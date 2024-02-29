package com.example.maxhodik.test.amazon.test.amazon.service;

import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByAsin;
import com.example.maxhodik.test.amazon.test.amazon.repository.SalesAsinRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class AsinServiceImpl implements AsinService {
    private final SalesAsinRepository asinRepository;

    public List<SalesAndTrafficByAsin> findByAsins(List<String> asins) {
        return asinRepository.findByParentAsinIn(asins);
    }

    @Override
    public List<SalesAndTrafficByAsin> findAllByAsins() {
        return asinRepository.findAll();
    }
}
