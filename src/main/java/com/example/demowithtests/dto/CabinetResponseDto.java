package com.example.demowithtests.dto;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.EmployeeCabinetRelation;

import java.util.Set;

public class CabinetResponseDto {
    public String name;

    public Integer capacity;

    public Set<Employee> employees;
}
