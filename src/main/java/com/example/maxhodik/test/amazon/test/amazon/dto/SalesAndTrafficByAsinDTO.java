package com.example.maxhodik.test.amazon.test.amazon.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class SalesAndTrafficByAsinDTO {

    private String parentAsin;
    private Map<String, Object> salesByAsin;
    private Map<String, Object> trafficByAsin;
}
