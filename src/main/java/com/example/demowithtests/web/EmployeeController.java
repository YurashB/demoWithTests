package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.PhotoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface EmployeeController {

    EmployeeDto saveEmployee(EmployeeDto requestForSave);


    List<EmployeeDto> getAllUsers();


    Page<EmployeeDto> getPage(int page, int size);


    EmployeeDto getEmployeeById(Integer id);


    EmployeeDto refreshEmployee(Integer id, Employee employee);

    void removeEmployeeById(Integer id);


    void removeAllUsers();

    Page<EmployeeDto> findByCountry(String country, int page, int size, List<String> sortList, Sort.Direction sortOrder);

    List<String> getAllUsersCountry();


    List<String> getAllUsersSort();

    Optional<String> getAllUsersSo();

    List<EmployeeDto> getUsersWithExpiredPhotos();

    EmployeeDto addNewPhotoToUser(int id, MultipartFile requestPhoto);


    PhotoDto getPhotoFromUser(int id);
}
