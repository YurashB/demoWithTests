package com.example.demowithtests.util.annotation;

import com.example.demowithtests.util.validators.EmployeeIdentifierValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmployeeIdentifierValidator.class)
public @interface EmployeeIdentifier {

    String message() default "Invalid identifier: must be like XX0000XX";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}

