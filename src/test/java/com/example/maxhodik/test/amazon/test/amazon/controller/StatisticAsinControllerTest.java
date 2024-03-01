package com.example.maxhodik.test.amazon.test.amazon.controller;

import com.example.maxhodik.test.amazon.test.amazon.TestUtils;
import com.example.maxhodik.test.amazon.test.amazon.dto.SalesAndTrafficByAsinDTO;
import com.example.maxhodik.test.amazon.test.amazon.service.AsinService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticAsinControllerTest {
    private Gson gson = new Gson();
    private final String salesByAsinDTO = TestUtils.readResource("sale.and.traffic.by.asin.json");

    private final SalesAndTrafficByAsinDTO salesAndTrafficByAsinDTO = gson.fromJson(salesByAsinDTO, SalesAndTrafficByAsinDTO.class);
    private final String asin = "B07JWCZKSJ";
    private final List<String> asinList = List.of(asin);
    @Mock

    private AsinService asinService;

    @InjectMocks
    private StatisticAsinController controller;

    @Test
    void findByAsin() {
        // GIVEN
        when(asinService.findByAsins(anyList())).thenReturn(List.of(salesAndTrafficByAsinDTO));
        // WHEN
        ResponseEntity<?> actual = controller.findByAsin(asinList);
        // THEN
        Assertions.assertEquals(HttpStatus.OK, actual.getStatusCode());
        Assertions.assertEquals(List.of(salesAndTrafficByAsinDTO), actual.getBody());
    }

    @Test
    void findByAsin_When_Asin_null() {
        // WHEN
        ResponseEntity<?> actual = controller.findByAsin(null);
        // THEN
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        Assertions.assertEquals("Invalid data", actual.getBody());
    }

    @Test
    void findByAsin_When_Asin_empty() {
        // WHEN
        ResponseEntity<?> actual = controller.findByAsin(List.of());
        // THEN
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        Assertions.assertEquals("Invalid data", actual.getBody());
    }

    @Test
    void findAllByAsin() {
        // GIVEN
        when(asinService.findAllByAsins()).thenReturn(List.of(salesAndTrafficByAsinDTO));
        // WHEN
        ResponseEntity<?> actual = controller.findAllByAsin();
        // THEN
        Assertions.assertEquals(HttpStatus.OK, actual.getStatusCode());
        Assertions.assertEquals(List.of(salesAndTrafficByAsinDTO), actual.getBody());
    }
}