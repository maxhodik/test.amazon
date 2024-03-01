package com.example.maxhodik.test.amazon.test.amazon.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class SalesAndTrafficByDateDTO {

    private String date;
    private Map<String, Object> salesByDate;
    private Map<String, Object> trafficByDate;
}