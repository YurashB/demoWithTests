package com.example.demowithtests.dto;

import com.example.demowithtests.domain.Gender;
import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.Photo;
import com.example.demowithtests.util.annotation.EmployeeIdentifier;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
@Setter
public class EmployeeReadDto {

    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public String name;

    public String country;

    @Email
    public String email;

    public Set<AddressDto> addresses = new HashSet<>();

    public Gender gender;

    @EmployeeIdentifier
    public String identifier;

    public Photo photo;

    public Passport passport;

}
