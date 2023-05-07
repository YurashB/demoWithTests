package com.example.demowithtests.dto;

import com.example.demowithtests.domain.Gender;
import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.util.annotation.EmployeeIdentifier;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDto {

    public Integer id;

    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public String name;

    @Schema(description = "Name of the country.", example = "England", required = true)
    public String country;

    @Schema(description = "Email address of an employee.", example = "billys@mail.com", required = true)
    public String email;

    public Set<AddressDto> addresses = new HashSet<>();

    public Gender gender;

    @EmployeeIdentifier
    public String identifier;

    public Set<PhotoDto> photos = new HashSet<>();

    public Passport passport;

}
