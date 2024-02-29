package com.example.maxhodik.test.amazon.test.amazon.dto;

import com.example.maxhodik.test.amazon.test.amazon.util.annotation.ValidDate;
import com.example.maxhodik.test.amazon.test.amazon.util.annotation.ValidDateRange;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@ValidDateRange
public class SearchDateRangeDTO {
    @NotNull
    @ValidDate
    private String startDate;
    @NotNull
    @ValidDate
    private String endDate;
}
