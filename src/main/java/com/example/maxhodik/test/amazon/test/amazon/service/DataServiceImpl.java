package com.example.maxhodik.test.amazon.test.amazon.service;

import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByDate;
import com.example.maxhodik.test.amazon.test.amazon.repository.SalesDataRepository;
import com.example.maxhodik.test.amazon.test.amazon.repository.SalesDateCustomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {
    private final SalesDataRepository dataRepository;
    private final SalesDateCustomRepository customRepository;


    @Override
    public List<SalesAndTrafficByDate> findByDateBetween(String startDate, String endDate) {
        return customRepository.findByDateBetweenInclusive(startDate, endDate);
    }

    @Override
    public List<SalesAndTrafficByDate> findByDate(String date) {
        return dataRepository.findByDate(date);
    }

    @Override
    public List<SalesAndTrafficByDate> findAll() {
        return dataRepository.findAll();
    }
}

