package com.example.demowithtests.dto;

import com.example.demowithtests.domain.PassportStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

public class PassportResponseDto {
    public String name;
    public LocalDate dateOfBirth;
    public String serialNumber;
    public LocalDate expireDate;
    public PassportStatus status;
    public PassportResponseDto prevPassport;
}