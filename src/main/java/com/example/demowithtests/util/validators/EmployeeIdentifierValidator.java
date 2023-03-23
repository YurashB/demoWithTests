package com.example.demowithtests.util.validators;

import com.example.demowithtests.util.annotation.EmployeeIdentifier;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmployeeIdentifierValidator implements ConstraintValidator<EmployeeIdentifier, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("[A-Z]{2}\\d{4}[A-Z]{2}");
    }

    @Override
    public void initialize(EmployeeIdentifier constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
