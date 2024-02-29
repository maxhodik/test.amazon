package com.example.maxhodik.test.amazon.test.amazon.service;

import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByAsin;

import java.util.List;

public interface AsinService {
    List<SalesAndTrafficByAsin> findByAsins(List<String> asins);

    List<SalesAndTrafficByAsin> findAllByAsins();
}

