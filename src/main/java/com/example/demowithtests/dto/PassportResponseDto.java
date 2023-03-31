package com.example.demowithtests.dto;

import com.example.demowithtests.domain.Employee;

import java.time.LocalDate;

public class PassportResponseDto {
    public String name;
    public LocalDate dateOfBirth;
    public String serialNumber;
    public LocalDate expireDate;
}