package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.Photo;
import com.example.demowithtests.domain.PhotoType;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.PhotoDto;
import com.example.demowithtests.repository.EmployeeRepository;
import com.example.demowithtests.repository.PassportRepository;
import com.example.demowithtests.util.config.mapstruct.EmployeeMapper;
import com.example.demowithtests.util.config.mapstruct.PhotoMapper;
import com.example.demowithtests.util.exception.PassportHasUserException;
import com.example.demowithtests.util.exception.ResourceNotFoundException;
import com.example.demowithtests.util.exception.ResourceWasDeletedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class EmployeeServiceBean implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final PhotoMapper photoMapper;
    private final EmployeeRepository employeeRepository;
    private final PassportRepository passportRepository;

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDto> getAll() {
        return employeeMapper.toDtoList(employeeRepository.findAll());
    }

    @Override
    public Page<EmployeeDto> getAllWithPagination(Pageable pageable) {
        // log.debug("getAllWithPagination() - start: pageable = {}", pageable);
        Page<Employee> list = employeeRepository.findAll(pageable);
        // log.debug("getAllWithPagination() - end: list = {}", list);
        return list.map(employeeMapper::toDto);
    }

    @Override
    public EmployeeDto getById(Integer id) {
        var employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));


        if (employee.getIsDeleted() == null) {
            employee.setIsDeleted(Boolean.TRUE);
        } else if (employee.getIsDeleted()) {
            throw new EntityNotFoundException("User try to access to deleted employee with id: " + id
                    + " where isDeleted field is true");
        }
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto updateById(Integer id, Employee employee) {
        return employeeMapper.toDto(employeeRepository.findById(id)
                .map(entity -> {
                    entity.setName(employee.getName());
                    entity.setEmail(employee.getEmail());
                    entity.setCountry(employee.getCountry());
                    return employeeRepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id)));
    }

    @Override
    public void removeById(Integer id) {
        //repository.deleteById(id);
        Employee employee = employeeRepository.findById(id)
                // .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                .orElseThrow(ResourceWasDeletedException::new);
        //employee.setIsDeleted(true);
        employeeRepository.delete(employee);
        //repository.save(employee);
    }

    @Override
    public void removeAll() {
        employeeRepository.deleteAll();
    }

    @Override
    public Page<EmployeeDto> findByCountryContaining(String country, int page, int size, List<
            String> sortList, String sortOrder) {
        // create Pageable object using the page, size and sort details
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        // fetch the page object by additionally passing pageable with the filters
        Page<Employee> list = employeeRepository.findByCountryContaining(country, pageable);
        return list.map(employeeMapper::toDto);
    }

    private List<Sort.Order> createSortOrder(List<String> sortList, String sortDirection) {
        List<Sort.Order> sorts = new ArrayList<>();
        Sort.Direction direction;
        for (String sort : sortList) {
            if (sortDirection != null) {
                direction = Sort.Direction.fromString(sortDirection);
            } else {
                direction = Sort.Direction.DESC;
            }
            sorts.add(new Sort.Order(direction, sort));
        }
        return sorts;
    }

    @Override
    public List<String> getAllEmployeeCountry() {
        //log.info("getAllEmployeeCountry() - start:");
        List<Employee> employeeList = employeeRepository.findAll();
        List<String> countries = employeeList.stream()
                .map(country -> country.getCountry())
                .collect(Collectors.toList());
        // log.info("getAllEmployeeCountry() - end: countries = {}", countries);
        return countries;
    }

    @Override
    public List<String> getSortCountry() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream()
                .map(Employee::getCountry)
                .filter(c -> c.startsWith("U"))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<String> findEmails() {
        var employeeList = employeeRepository.findAll();

        var emails = employeeList.stream()
                .map(Employee::getEmail)
                .collect(Collectors.toList());

        var opt = emails.stream()
                .filter(s -> s.endsWith(".com"))
                .findFirst()
                .orElse("error?");
        return Optional.ofNullable(opt);
    }

    @Override
    public List<EmployeeDto> findEmployeesWithExpiredPhotos() {
        List<Employee> employees = employeeRepository.findAll();
        Comparator<Photo> photoComparator = Comparator.comparing(Photo::getAddDate);
        List<Employee> employeeWithExpiredPhotos = new ArrayList<>();

        for (Employee employee : employees) {
            TreeSet<Photo> photos = new TreeSet<>(photoComparator);
            photos.addAll(employee.getPhotos());

            if (!photos.isEmpty() && photos.last().getAddDate().isBefore(LocalDateTime.now().plusDays(8).minusYears(5))) {
                employeeWithExpiredPhotos.add(employee);
            }
        }

        return employeeMapper.toDtoList(employeeWithExpiredPhotos);
    }

    @Override
    public EmployeeDto addEmployeePhoto(int employeeId, MultipartFile multipartFile) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new com.example.demowithtests.util.exception.EntityNotFoundException(new Employee(), employeeId));
        if (multipartFile.isEmpty()) throw new ResourceNotFoundException("Photo of employee not found or empty");

        try {
            Photo photo = new Photo();
            photo.setPhotoType(PhotoType.getType(Objects.requireNonNull(multipartFile.getContentType())));
            photo.setAddDate(LocalDateTime.now());
            photo.setImage(multipartFile.getBytes());

            employee.getPhotos().add(photo);
            Employee save = employeeRepository.save(employee);
            return employeeMapper.toDto(save);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Something go wrong when add photo");
        }
    }

    @Override
    public PhotoDto getEmployeePhoto(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new com.example.demowithtests.util.exception.EntityNotFoundException(new Employee(), employeeId));

        Optional<Photo> photo = employee.getPhotos().stream().max(Comparator.comparing(Photo::getAddDate));

        if (photo.isPresent()) {
            return photoMapper.toDto(photo.get());
        } else {
            throw new ResourceNotFoundException("There is no any photo in employee with id: " + employeeId);
        }

    }

    @Override
    public EmployeeDto addPassportToEmployee(Integer passportId, Integer employeeId) {
        Passport passport = passportRepository.findById(passportId)
                .orElseThrow(() -> new ResourceNotFoundException("Passport with id" + passportId + " was not found"));
        if (passport.getEmployee() != null) {
            throw new PassportHasUserException();
        }

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id" + employeeId + " was not found"));
        employee.setPassport(passport);
        employeeRepository.addPassportToEmployee(employeeId, passport);
        return employeeMapper.toDto(employee);
    }
}