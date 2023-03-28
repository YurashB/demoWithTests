package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.PhotoDto;
import com.example.demowithtests.service.EmployeeService;
import com.example.demowithtests.util.config.mapstruct.EmployeeMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Employee", description = "Employee API")
public class EmployeeControllerBean implements EmployeeControllerSwagger {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto saveEmployee(@RequestBody @Valid EmployeeDto requestForSave) {

        var employee = employeeMapper.toModel(requestForSave);
        var dto = employeeMapper.ToDto(employeeService.create(employee));

        return dto;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> getAllUsers() {
        return employeeService.getAll();
    }

    @GetMapping("/users/p")
    @ResponseStatus(HttpStatus.OK)
    public Page<EmployeeDto> getPage(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        return (employeeService.getAllWithPagination(paging));
    }

    @GetMapping("/users/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Integer id) {
        return employeeService.getById(id);
    }

    //Обновление юзера
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto refreshEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {

        return employeeService.updateById(id, employee);
    }

    //Удаление по id
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable Integer id) {
        employeeService.removeById(id);
    }

    //Удаление всех юзеров
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        employeeService.removeAll();
    }

    @GetMapping("/users/country")
    @ResponseStatus(HttpStatus.OK)
    public Page<EmployeeDto> findByCountry(@RequestParam(required = false) String country,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "3") int size,
                                           @RequestParam(defaultValue = "") List<String> sortList,
                                           @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder) {
        //Pageable paging = PageRequest.of(page, size);
        //Pageable paging = PageRequest.of(page, size, Sort.by("name").ascending());
        return employeeService.findByCountryContaining(country, page, size, sortList, sortOrder.toString());
    }

    @GetMapping("/users/c")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllUsersCountry() {
        return employeeService.getAllEmployeeCountry();
    }

    @GetMapping("/users/s")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllUsersSort() {
        return employeeService.getSortCountry();
    }

    @GetMapping("/users/emails")
    @ResponseStatus(HttpStatus.OK)
    public Optional<String> getAllUsersSo() {
        return employeeService.findEmails();
    }

    @GetMapping("/users/with-expired-photos")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> getUsersWithExpiredPhotos() {
        return employeeService.findEmployeesWithExpiredPhotos();
    }

    @PostMapping("/users/{id}/photo")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto addNewPhotoToUser(@PathVariable int id, @RequestParam("file") MultipartFile requestPhoto) {
        return employeeService.addEmployeePhoto(id, requestPhoto);
    }

    @GetMapping("/users/{id}/photo")
    @ResponseStatus(HttpStatus.OK)
    public PhotoDto getPhotoFromUser(@PathVariable int id) {
        return employeeService.getEmployeePhoto(id);
    }
}
