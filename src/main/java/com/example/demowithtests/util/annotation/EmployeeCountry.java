package com.example.demowithtests.util.annotation;

import com.example.demowithtests.domain.Gender;
import com.example.demowithtests.util.validators.EmployeeCountryValidator;
import com.example.demowithtests.util.validators.EmployeeIdentifierValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmployeeCountryValidator.class)
public @interface EmployeeCountry {

    String message() default "Invalid country name: must start of upper case and not be null";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
