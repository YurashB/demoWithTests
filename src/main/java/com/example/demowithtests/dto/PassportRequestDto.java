package com.example.demowithtests.dto;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@ToString
public class PassportRequestDto {

    public String name;
    public LocalDate dateOfBirth;
    public LocalDate expireDate;
}
