package com.example.demowithtests.web.employeeControllers;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.PhotoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Tag(name = "Employee", description = "The employee API")
public interface EmployeeControllerSwagger extends EmployeeController {

    @Override
    @Operation(summary = "Endpoint to add a new employee.", description = "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    EmployeeDto saveEmployee(EmployeeDto requestForSave);

    @Override
    @Operation(summary = "Endpoint to get all employee.", description = "Create request to get all employees.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. All user can be read"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. There is no any employee")})
    List<EmployeeDto> getAllUsers();

    @Override
    @Operation(summary = "Endpoint to get page of employee.", description = "Create request to get page of employees.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. page of employee can be read"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. There is no any employee")})
    Page<EmployeeDto> getPage(int page, int size);

    @Override
    @Operation(summary = "Endpoint returned a employee by his id.", description = "Create request to read a employee by id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    EmployeeDto getEmployeeById(Integer id);

    @Override
    @Operation(summary = "Endpoint to update some filed of employee", description = "Create request to update field of employee", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Employee was refreshed"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found.")})
    EmployeeDto refreshEmployee(Integer id, Employee employee);

    @Override
    @Operation(summary = "Endpoint to delete employee", description = "Create request to delete employee", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Employee was deleted"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found.")})
    void removeEmployeeById(Integer id);

    @Override
    @Operation(summary = "Endpoint to delete all employees", description = "Create request to delete employees", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Employees was deleted")})
    void removeAllUsers();

    @Override
    @Operation(summary = "Endpoint to get employee.", description = "Create request get employees.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. There is no any employee")})
    Page<EmployeeDto> findByCountry(String country, int page, int size, List<String> sortList, Sort.Direction sortOrder);

    @Override
    @Operation(summary = "Endpoint to get employee country.", description = "Create request get employee country", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. There is no any employee")})
    List<String> getAllUsersCountry();

    @Override
    @Operation(summary = "Endpoint to get sort employee .", description = "Create request get sorted employee", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. There is no any employee")})
    List<String> getAllUsersSort();

    @Override
    @Operation(summary = "Unknown, don`t use that.", description = "Unknown, don`t use that", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")})
    Optional<String> getAllUsersSo();

    @Override
    @Operation(summary = "Endpoint to get user with expired photos", description = "Get employee with expired photos", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")})
    List<EmployeeDto> getUsersWithExpiredPhotos();

    @Override
    @Operation(summary = "Endpoint to add photo to employee", description = "add photo to employee", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. There is no  employee with id")})
    EmployeeDto addNewPhotoToUser(int id, MultipartFile requestPhoto);

    @Override
    @Operation(summary = "Endpoint to get photo of employee", description = "get photo of employee", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. There is no  employee with id")})
    PhotoDto getPhotoFromUser(int id);

    @Override
    @Operation(summary = "Endpoint to add passport to employee", description = "add passport to employee", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "405", description = "Can`t add passport to user because passport have owner")})
    EmployeeDto addPassportToEmployee(int passportId, int userId);
}
