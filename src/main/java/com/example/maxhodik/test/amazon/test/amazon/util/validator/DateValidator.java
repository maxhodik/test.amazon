package com.example.maxhodik.test.amazon.test.amazon.util.validator;

import com.example.maxhodik.test.amazon.test.amazon.util.annotation.ValidDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateValidator implements ConstraintValidator<ValidDate, String> {

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    @Override
    public void initialize(ValidDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        try {
            LocalDate.parse(value, DateTimeFormatter.ofPattern(DATE_PATTERN));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
