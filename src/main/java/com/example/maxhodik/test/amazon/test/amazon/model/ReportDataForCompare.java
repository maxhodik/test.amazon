package com.example.maxhodik.test.amazon.test.amazon.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "report_data_for_compare")
public class ReportDataForCompare {

    @Id
    private String id;
    private String reportData;

    public ReportDataForCompare(String reportData) {
        this.reportData = reportData;
    }
}
