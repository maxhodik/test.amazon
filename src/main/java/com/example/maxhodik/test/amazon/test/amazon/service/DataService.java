package com.example.maxhodik.test.amazon.test.amazon.service;

import com.example.maxhodik.test.amazon.test.amazon.dto.SalesAndTrafficByDateDTO;

import java.util.List;

public interface DataService {


    List<SalesAndTrafficByDateDTO> findByDateBetween(String startDate, String endDate);

    List<SalesAndTrafficByDateDTO> findByDate(String date);

    List<SalesAndTrafficByDateDTO> findAllByDate();
}


