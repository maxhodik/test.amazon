package com.example.maxhodik.test.amazon.test.amazon.dto;

import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByDate;
import lombok.Data;

import java.util.List;
@Data
public class ReportDataDateDTO {
    private ReportSpecificationDTO reportSpecification;
    private List<SalesAndTrafficByDate> salesAndTrafficByDate;

}
