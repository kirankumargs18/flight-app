package com.globallogic.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TimeValidatorImpl.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeValidator {

    String message() default "Arrival time should be less than departure time";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
