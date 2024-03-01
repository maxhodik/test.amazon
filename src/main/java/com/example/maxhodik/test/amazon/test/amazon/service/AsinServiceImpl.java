package com.example.maxhodik.test.amazon.test.amazon.service;

import com.example.maxhodik.test.amazon.test.amazon.dto.SalesAndTrafficByAsinDTO;
import com.example.maxhodik.test.amazon.test.amazon.mapper.SalesAndTrafficByAsinMapper;
import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByAsin;
import com.example.maxhodik.test.amazon.test.amazon.repository.SalesAsinRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@Data
@RequiredArgsConstructor
public class AsinServiceImpl implements AsinService {
    private final SalesAsinRepository asinRepository;
    private final SalesAndTrafficByAsinMapper mapper;

    @Override
    @Cacheable("findByAsins")
    public List<SalesAndTrafficByAsinDTO> findByAsins(List<String> asins) {
        log.info("Finding by asins{}", asins);
        return getSalesAndTrafficByAsinDTOS(asinRepository.findByParentAsinIn(asins));

    }

    @Override
    @Cacheable("findAllByAsins")
    public List<SalesAndTrafficByAsinDTO> findAllByAsins() {
        log.info("finding all by asin");
        return getSalesAndTrafficByAsinDTOS(asinRepository.findAll());
    }

    private List<SalesAndTrafficByAsinDTO> getSalesAndTrafficByAsinDTOS(List<SalesAndTrafficByAsin> asinRepository) {
        return asinRepository
                .stream()
                .map(mapper::mapToSalesDTO)
                .toList();
    }
}
