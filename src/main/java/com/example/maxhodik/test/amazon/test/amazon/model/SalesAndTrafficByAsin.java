package com.example.maxhodik.test.amazon.test.amazon.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
@Data
@Document(collection="sales_asin")
public class SalesAndTrafficByAsin {
    @Id
    private String id;
    private String parentAsin;
    private Map<String, Object> salesByAsin;
    private Map<String, Object> trafficByAsin;
}
