package com.example.maxhodik.test.amazon.test.amazon.service;

import com.example.maxhodik.test.amazon.test.amazon.model.ReportData;
import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByDate;

import java.time.LocalDate;
import java.util.List;

public interface GettingDataService {
    List<SalesAndTrafficByDate> findAllByDate();

    List<ReportData>  findByDateRange(List<LocalDate> dates);


}
