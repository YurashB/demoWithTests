package com.example.demowithtests.util;

import com.example.demowithtests.util.validator.PhotoUrlValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhotoUrlValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhotoUrl {
    String message() default "Invalid photo url";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

