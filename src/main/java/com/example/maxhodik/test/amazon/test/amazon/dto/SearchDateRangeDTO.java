package com.example.maxhodik.test.amazon.test.amazon.dto;

import com.example.maxhodik.test.amazon.test.amazon.util.annotation.ValidDate;
import com.example.maxhodik.test.amazon.test.amazon.util.annotation.ValidDateRange;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ValidDateRange
@AllArgsConstructor
public class SearchDateRangeDTO {
    @NotNull
    @ValidDate
    private String startDate;
    @NotNull
    @ValidDate
    private String endDate;
}
