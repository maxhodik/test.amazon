package com.example.maxhodik.test.amazon.test.amazon.service;

import com.example.maxhodik.test.amazon.test.amazon.TestUtils;
import com.example.maxhodik.test.amazon.test.amazon.dto.SalesAndTrafficByAsinDTO;
import com.example.maxhodik.test.amazon.test.amazon.mapper.SalesAndTrafficByAsinMapper;
import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByAsin;
import com.example.maxhodik.test.amazon.test.amazon.repository.SalesAsinRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AsinServiceImplTest {

    private final String salesByAsin =
            TestUtils.readResource("sale.and.traffic.by.asin.json");
    Gson gson = new Gson();
    private final SalesAndTrafficByAsin salesAndTrafficByAsin = gson.fromJson(salesByAsin, SalesAndTrafficByAsin.class);
    private final String salesByAsinDTO =
            TestUtils.readResource("sale.and.traffic.by.asin.json");
    private final SalesAndTrafficByAsinDTO salesAndTrafficByAsinDTO = gson.fromJson(salesByAsin, SalesAndTrafficByAsinDTO.class);
    private final List<SalesAndTrafficByAsin> salesAndTrafficByAsinList = List.of(salesAndTrafficByAsin);
    private final List<SalesAndTrafficByAsinDTO> salesDTOList = List.of(salesAndTrafficByAsinDTO);
    private final String asin = "B07JWCZKSJ";
    private final List<String> asinList = List.of(asin);
    @Mock
    private SalesAsinRepository asinRepository;
    @Mock
    private SalesAndTrafficByAsinMapper mapper;
    @InjectMocks
    private AsinServiceImpl asinService;


    @Test
    void findByAsins() {
        // GIVEN
        when(asinRepository.findByParentAsinIn(any())).thenReturn(salesAndTrafficByAsinList);
        when(mapper.mapToSalesDTO(any())).thenReturn(salesAndTrafficByAsinDTO);
        // WHEN
        List<SalesAndTrafficByAsinDTO> actual = asinService.findByAsins(asinList);
        // THEN
        verify(mapper).mapToSalesDTO(salesAndTrafficByAsin);
        Assertions.assertEquals(salesDTOList, actual);
    }

    @Test
    void findAllByAsins() {
        // GIVEN
        when(asinRepository.findAll()).thenReturn(salesAndTrafficByAsinList);
        when(mapper.mapToSalesDTO(any())).thenReturn(salesAndTrafficByAsinDTO);
        // WHEN
        List<SalesAndTrafficByAsinDTO> actual = asinService.findAllByAsins();
        // THEN
        verify(mapper).mapToSalesDTO(salesAndTrafficByAsin);
        Assertions.assertEquals(salesDTOList, actual);
    }


}