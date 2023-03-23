package com.example.demowithtests.util.validators;

import com.example.demowithtests.util.annotation.EmployeeCountry;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmployeeCountryValidator implements ConstraintValidator<EmployeeCountry, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.matches("\\W|\\d") && Character.isUpperCase(value.charAt(0));
    }

    @Override
    public void initialize(EmployeeCountry constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
