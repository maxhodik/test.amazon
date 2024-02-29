package com.example.maxhodik.test.amazon.test.amazon.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReportSpecificationDTO {
    private String reportType;
    private ReportOptionsDTO reportOptions;
    private String dataStartTime;
    private String dataEndTime;
    private List<String> marketplaceIds;
}