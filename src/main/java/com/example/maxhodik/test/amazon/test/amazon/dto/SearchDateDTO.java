package com.example.maxhodik.test.amazon.test.amazon.dto;

import com.example.maxhodik.test.amazon.test.amazon.util.annotation.ValidDate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDateDTO {
    @NotNull
    @ValidDate
    private String date;
}
