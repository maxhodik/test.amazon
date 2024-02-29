package com.example.maxhodik.test.amazon.test.amazon.util.validator;


import com.example.maxhodik.test.amazon.test.amazon.dto.SearchDateRangeDTO;
import com.example.maxhodik.test.amazon.test.amazon.util.annotation.ValidDateRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, SearchDateRangeDTO> {

    @Override
    public void initialize(ValidDateRange constraintAnnotation) {
    }

    @Override
    public boolean isValid(SearchDateRangeDTO value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(value.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            endDate = LocalDate.parse(value.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            return false;
        }

        return startDate.isBefore(endDate) || startDate.isEqual(endDate);
    }
}
