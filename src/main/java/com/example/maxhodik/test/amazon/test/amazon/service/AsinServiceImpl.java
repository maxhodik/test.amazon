package com.example.maxhodik.test.amazon.test.amazon.service;

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

    @Override
    @Cacheable("findByAsins")
    public List<SalesAndTrafficByAsin> findByAsins(List<String> asins) {
        log.info("Finding by asins{}", asins);
        return asinRepository.findByParentAsinIn(asins);
    }

    @Override
    @Cacheable("findAllByAsins")
    public List<SalesAndTrafficByAsin> findAllByAsins() {
        log.info("finding all by asin");
        return asinRepository.findAll();
    }
}
