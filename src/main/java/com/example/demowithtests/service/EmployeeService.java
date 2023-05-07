package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.PhotoDto;
import com.example.demowithtests.domain.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee create(Employee employee);

    List<EmployeeDto> getAll();

    Page<EmployeeDto> getAllWithPagination(Pageable pageable);

    EmployeeDto getById(Integer id);

    EmployeeDto updateById(Integer id, Employee plane);

    void removeById(Integer id);

    void removeAll();

    /**
     * @param country  Filter for the country if required
     * @param page            number of the page returned
     * @param size            number of entries in each page
     * @param sortList        list of columns to sort on
     * @param sortOrder       sort order. Can be ASC or DESC
     * @return Page object with customers after filtering and sorting
     */
    Page<EmployeeDto> findByCountryContaining(String country, int page, int size, List<String> sortList, String sortOrder);

    List<String> getAllEmployeeCountry();

    List<String> getSortCountry();

    Optional<String> findEmails();

    List<EmployeeDto> findEmployeesWithExpiredPhotos();

    EmployeeDto addEmployeePhoto(int employeeIs, MultipartFile multipartFile);

    PhotoDto getEmployeePhoto(int employeeId);

    EmployeeDto addPassportToEmployee(LocalDate dateOfBirth, Integer passportId);

}
