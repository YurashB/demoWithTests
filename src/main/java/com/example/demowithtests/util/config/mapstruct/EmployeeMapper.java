package com.example.demowithtests.util.config.mapstruct;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface EmployeeMapper {

    @Mapping(source = "addresses", target = "addresses")
    EmployeeDto toDto(Employee employee);

    Employee toModel(EmployeeDto employeeDto);

    List<EmployeeDto> toDtoList(List<Employee> employees);

    EmployeeReadDto toReadDto(Employee employee);

    Employee toModel(EmployeeReadDto employeeReadDto);

    List<Employee> ToModel(List<Employee> all);
}
