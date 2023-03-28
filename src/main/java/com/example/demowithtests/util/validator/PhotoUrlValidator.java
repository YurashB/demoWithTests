package com.example.demowithtests.util.validator;

import com.example.demowithtests.util.validator.annotations.PhotoUrl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class PhotoUrlValidator implements ConstraintValidator<PhotoUrl, String> {

    @Override
    public void initialize(PhotoUrl constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            new URL(value).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }
}
