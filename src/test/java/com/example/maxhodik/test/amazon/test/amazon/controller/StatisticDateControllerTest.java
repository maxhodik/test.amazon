package com.example.maxhodik.test.amazon.test.amazon.controller;

import com.example.maxhodik.test.amazon.test.amazon.TestUtils;
import com.example.maxhodik.test.amazon.test.amazon.dto.SalesAndTrafficByDateDTO;
import com.example.maxhodik.test.amazon.test.amazon.dto.SearchDateDTO;
import com.example.maxhodik.test.amazon.test.amazon.dto.SearchDateRangeDTO;
import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByDate;
import com.example.maxhodik.test.amazon.test.amazon.service.DateService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticDateControllerTest {
    private static final String DATE = "2024-02-14";
    private static final String END_DATE = "2024-02-16";
    private final SearchDateRangeDTO dateRangeDTO = new SearchDateRangeDTO(DATE, END_DATE);
    private final SearchDateRangeDTO wrongDateRangeDTO = new SearchDateRangeDTO(DATE, null);

    private final SearchDateDTO dateDTO = new SearchDateDTO(DATE);
    private final String salesByDate = TestUtils.readResource("sale.and.traffic.by.asin.json");
    private Gson gson = new Gson();
    private final SalesAndTrafficByDate salesAndTrafficByDate = gson.fromJson(salesByDate, SalesAndTrafficByDate.class);
    private final SalesAndTrafficByDateDTO salesAndTrafficByDateDTO = gson.fromJson(salesByDate, SalesAndTrafficByDateDTO.class);
    private final List<SalesAndTrafficByDate> salesAndTrafficByDateList = List.of(salesAndTrafficByDate);
    private final List<SalesAndTrafficByDateDTO> salesAndTrafficByDateDTOList = List.of(salesAndTrafficByDateDTO);
    @Mock
    private DateService dateService;
    @Mock
    private BindingResult result;
    @InjectMocks
    private StatisticDateController controller;

    @Test
    void findByDateRange() {
        // GIVEN
        when(dateService.findByDateBetween(anyString(), anyString())).thenReturn(salesAndTrafficByDateDTOList);
        // WHEN
        ResponseEntity<?> actual = controller.findByDateRange(dateRangeDTO, result);
        // THEN
        Assertions.assertEquals(HttpStatus.OK, actual.getStatusCode());
        Assertions.assertEquals(salesAndTrafficByDateDTOList, actual.getBody());
    }

    @Test
    void findByDateRange_BadDateRange() {
        // GIVEN
        when(result.hasErrors()).thenReturn(true);
        // WHEN
        ResponseEntity<?> actual = controller.findByDateRange(wrongDateRangeDTO, result);
        // THEN
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        Assertions.assertEquals("Validation error(s): ", actual.getBody());
    }

    @Test
    void findByDate() {
        // GIVEN
        when(dateService.findByDate(anyString())).thenReturn(salesAndTrafficByDateDTOList);
        // WHEN
        ResponseEntity<?> actual = controller.findByDate(dateDTO, result);
        // THEN
        Assertions.assertEquals(HttpStatus.OK, actual.getStatusCode());
        Assertions.assertEquals(salesAndTrafficByDateDTOList, actual.getBody());
    }

    @Test
    void findByDate_WrongDate() {
        // GIVEN
        when(result.hasErrors()).thenReturn(true);
        // WHEN
        ResponseEntity<?> actual = controller.findByDate(dateDTO, result);
        // THEN
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        Assertions.assertEquals("Validation error(s): ", actual.getBody());
    }

    @Test
    void findAllByDate() {
        // GIVEN
        when(dateService.findAllByDate()).thenReturn(salesAndTrafficByDateDTOList);
        // WHEN
        ResponseEntity<?> actual = controller.findAllByDate();
        // THEN
        Assertions.assertEquals(HttpStatus.OK, actual.getStatusCode());
        Assertions.assertEquals(salesAndTrafficByDateDTOList, actual.getBody());
    }
}