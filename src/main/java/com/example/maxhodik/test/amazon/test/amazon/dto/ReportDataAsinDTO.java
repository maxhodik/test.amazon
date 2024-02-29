package com.example.maxhodik.test.amazon.test.amazon.dto;

import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByAsin;
import lombok.Data;

import java.util.List;
@Data
public class ReportDataAsinDTO {
    private ReportSpecificationDTO reportSpecification;
    private List<SalesAndTrafficByAsin> salesAndTrafficByAsin;
}
