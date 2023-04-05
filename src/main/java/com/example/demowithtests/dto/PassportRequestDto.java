package com.example.demowithtests.dto;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.PassportStatus;
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
    public PassportStatus status;
}
