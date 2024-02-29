package com.example.maxhodik.test.amazon.test.amazon.dto;

import com.example.maxhodik.test.amazon.test.amazon.util.annotation.ValidDate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SearchDateDTO {
    @NotNull
    @ValidDate
    private String date;
}
