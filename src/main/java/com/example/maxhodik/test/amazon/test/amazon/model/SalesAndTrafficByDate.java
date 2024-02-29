package com.example.maxhodik.test.amazon.test.amazon.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
@Data
@Document(collection="sales_date")
public class SalesAndTrafficByDate {
    @Id
    private String id;
    private String date;
    private Map<String, Object> salesByDate;
    private Map<String, Object> trafficByDate;
}
