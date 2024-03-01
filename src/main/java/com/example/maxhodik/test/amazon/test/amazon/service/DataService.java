package com.example.maxhodik.test.amazon.test.amazon.service;

import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByDate;

import java.util.List;

public interface DataService {


    List<SalesAndTrafficByDate> findByDateBetween(String startDate, String endDate);

    List<SalesAndTrafficByDate> findByDate(String date);

    List<SalesAndTrafficByDate> findAllByDate();
}


