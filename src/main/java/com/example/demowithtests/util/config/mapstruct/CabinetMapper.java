package com.example.demowithtests.util.config.mapstruct;

import com.example.demowithtests.domain.Cabinet;
import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.EmployeeCabinetRelation;
import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.dto.CabinetRequestDto;
import com.example.demowithtests.dto.CabinetResponseDto;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.PassportResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = CabinetMapper.class)
public interface CabinetMapper {

    @Mapping(target = "capacity", defaultValue = "1")
    @Mapping(target = "employees", expression = "java(getEmployeeFromCabinets(cabinet.getEmployees()))")
    CabinetResponseDto toDto(Cabinet cabinet);

    Cabinet toModel(CabinetRequestDto cabinet);

    default Set<Employee> getEmployeeFromCabinets(Set<EmployeeCabinetRelation> employeeCabinetRelations) {
        Set<Employee> collect = employeeCabinetRelations
                .stream()
                .map(EmployeeCabinetRelation::getEmployee)
                .collect(Collectors.toSet());

        collect.forEach(employee -> employee.setCabinets(null));
        collect.forEach(employee -> employee.setPassport(null));
        collect.forEach(employee -> employee.setPhotos(null));
        return collect;
    }
}
