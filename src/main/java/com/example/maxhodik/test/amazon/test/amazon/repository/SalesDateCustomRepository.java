package com.example.maxhodik.test.amazon.test.amazon.repository;

import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByDate;

import java.util.List;

public interface SalesDateCustomRepository {
    List<SalesAndTrafficByDate> findByDateBetweenInclusive(String startDate, String endDate);
}
