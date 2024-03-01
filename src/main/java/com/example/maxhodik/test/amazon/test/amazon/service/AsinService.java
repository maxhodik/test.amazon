package com.example.maxhodik.test.amazon.test.amazon.service;

import com.example.maxhodik.test.amazon.test.amazon.dto.SalesAndTrafficByAsinDTO;

import java.util.List;

public interface AsinService {
    List<SalesAndTrafficByAsinDTO> findByAsins(List<String> asins);

    List<SalesAndTrafficByAsinDTO> findAllByAsins();
}

