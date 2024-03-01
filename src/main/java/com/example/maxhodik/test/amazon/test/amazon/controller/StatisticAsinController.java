package com.example.maxhodik.test.amazon.test.amazon.controller;

import com.example.maxhodik.test.amazon.test.amazon.dto.SalesAndTrafficByAsinDTO;
import com.example.maxhodik.test.amazon.test.amazon.service.AsinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/statistic/asin")
@RequiredArgsConstructor
public class StatisticAsinController {

    private final AsinService asinService;


    // Вивід статистики по вказаному ASIN (або списку ASINs)
    @PostMapping("/search")
    public ResponseEntity<?> findByAsin(@RequestBody List<String> asins) {
        if (asins == null || CollectionUtils.isEmpty(asins)) {
            return ResponseEntity.badRequest().body("Invalid data");
        }
        List<SalesAndTrafficByAsinDTO> byAsinIn = asinService.findByAsins(asins);
        log.info("Search by asins {}", asins);
        return ResponseEntity.ok(byAsinIn);
    }

    //   Вивід сумарної статистики по всім ASIN
    @GetMapping
    public ResponseEntity<?> findAllByAsin() {
        List<SalesAndTrafficByAsinDTO> byAsin = asinService.findAllByAsins();
        log.info("Search all by asins");
        return ResponseEntity.ok(byAsin);
    }
}
