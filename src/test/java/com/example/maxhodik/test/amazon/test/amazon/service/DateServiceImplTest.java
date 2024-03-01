package com.example.maxhodik.test.amazon.test.amazon.service;

import com.example.maxhodik.test.amazon.test.amazon.TestUtils;
import com.example.maxhodik.test.amazon.test.amazon.dto.SalesAndTrafficByDateDTO;
import com.example.maxhodik.test.amazon.test.amazon.mapper.SalesAndTrafficByDateMapper;
import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByDate;
import com.example.maxhodik.test.amazon.test.amazon.repository.SalesDataRepository;
import com.example.maxhodik.test.amazon.test.amazon.repository.SalesDateCustomRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DateServiceImplTest {
    private static final String DATE = "2024-02-14";
    private static final String END_DATE = "2024-02-16";
    private final String salesByDate = TestUtils.readResource("sale.and.traffic.by.asin.json");
    private Gson gson = new Gson();
    private final SalesAndTrafficByDate salesAndTrafficByDate = gson.fromJson(salesByDate, SalesAndTrafficByDate.class);
    private final SalesAndTrafficByDateDTO salesAndTrafficByDateDTO = gson.fromJson(salesByDate, SalesAndTrafficByDateDTO.class);
    private final List<SalesAndTrafficByDate> salesAndTrafficByDateList = List.of(salesAndTrafficByDate);
    private final List<SalesAndTrafficByDateDTO> salesAndTrafficByDateDTOList = List.of(salesAndTrafficByDateDTO);
    @Mock
    private SalesDataRepository dataRepository;
    @Mock
    private SalesDateCustomRepository customRepository;
    @Mock
    private SalesAndTrafficByDateMapper mapper;
    @InjectMocks
    private DateServiceImpl dataService;

    @Test
    void findByDateBetween() {
        // GIVEN
        when(customRepository.findByDateBetweenInclusive(anyString(), anyString())).thenReturn(salesAndTrafficByDateList);
        when(mapper.mapToSalesDTO(any(SalesAndTrafficByDate.class))).thenReturn(salesAndTrafficByDateDTO);
        // WHEN
        List<SalesAndTrafficByDateDTO> actual = dataService.findByDateBetween(DATE, END_DATE);
        // THEN
        verify(mapper).mapToSalesDTO(salesAndTrafficByDate);
        Assertions.assertEquals(salesAndTrafficByDateDTOList, actual);
    }

    @Test
    void findByDate() {
        // GIVEN
        when(dataRepository.findByDate(anyString())).thenReturn(salesAndTrafficByDateList);
        when(mapper.mapToSalesDTO(any(SalesAndTrafficByDate.class))).thenReturn(salesAndTrafficByDateDTO);
        // WHEN
        List<SalesAndTrafficByDateDTO> actual = dataService.findByDate(DATE);
        // THEN
        verify(mapper).mapToSalesDTO(salesAndTrafficByDate);
        Assertions.assertEquals(salesAndTrafficByDateDTOList, actual);
    }

    @Test
    void findAllByDate() {
        // GIVEN
        when(dataRepository.findAll()).thenReturn(salesAndTrafficByDateList);
        when(mapper.mapToSalesDTO(any(SalesAndTrafficByDate.class))).thenReturn(salesAndTrafficByDateDTO);
        // WHEN
        List<SalesAndTrafficByDateDTO> actual = dataService.findAllByDate();
        // THEN
        verify(mapper).mapToSalesDTO(salesAndTrafficByDate);
        Assertions.assertEquals(salesAndTrafficByDateDTOList, actual);
    }
}