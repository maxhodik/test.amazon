package com.example.maxhodik.test.amazon.test.amazon.service;

import com.example.maxhodik.test.amazon.test.amazon.dto.SalesAndTrafficByDateDTO;
import com.example.maxhodik.test.amazon.test.amazon.mapper.SalesAndTrafficByDateMapper;
import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByDate;
import com.example.maxhodik.test.amazon.test.amazon.repository.SalesDataRepository;
import com.example.maxhodik.test.amazon.test.amazon.repository.SalesDateCustomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {
    private final SalesDataRepository dataRepository;
    private final SalesDateCustomRepository customRepository;
    private final SalesAndTrafficByDateMapper mapper;


    @Override
    @Cacheable("findByDateBetween")
    public List<SalesAndTrafficByDateDTO> findByDateBetween(String startDate, String endDate) {
        log.info("Finding by date between {} - {}", startDate, endDate);
        return getSalesAndTrafficByDateDTOS(customRepository.findByDateBetweenInclusive(startDate, endDate));

    }

    @Override
    @Cacheable("findByDate")
    public List<SalesAndTrafficByDateDTO> findByDate(String date) {
        log.info("Finding by date {}", date);
        return getSalesAndTrafficByDateDTOS(dataRepository.findByDate(date));
    }

    @Override
    @Cacheable("findAllByDate")
    public List<SalesAndTrafficByDateDTO> findAllByDate() {
        log.info("Finding all by date ");
        return getSalesAndTrafficByDateDTOS(dataRepository.findAll());
    }

    private List<SalesAndTrafficByDateDTO> getSalesAndTrafficByDateDTOS(List<SalesAndTrafficByDate> listByDate) {
        return listByDate.stream()
                .map(mapper::mapToSalesDTO)
                .toList();
    }
}

