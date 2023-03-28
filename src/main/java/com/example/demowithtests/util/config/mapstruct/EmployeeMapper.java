package com.example.demowithtests.util.config.mapstruct;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Photo;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface EmployeeMapper {

    @Mapping(source = "addresses", target = "addresses")
     EmployeeDto ToDto(Employee employee);

    Employee toModel(EmployeeDto employeeDto);

    List<EmployeeDto> ToDtoList(List<Employee> employees);

    EmployeeReadDto toReadDto(Employee employee);

    Employee toModel(EmployeeReadDto employeeReadDto);

    List<Employee> ToModel(List<Employee> all);
}
