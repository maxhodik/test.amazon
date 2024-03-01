package com.example.maxhodik.test.amazon.test.amazon.controller;

import com.example.maxhodik.test.amazon.test.amazon.dto.SalesAndTrafficByDateDTO;
import com.example.maxhodik.test.amazon.test.amazon.dto.SearchDateDTO;
import com.example.maxhodik.test.amazon.test.amazon.dto.SearchDateRangeDTO;
import com.example.maxhodik.test.amazon.test.amazon.repository.ReportDataRepository;
import com.example.maxhodik.test.amazon.test.amazon.service.DataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic/dates")

public class StatisticDateController {

    private final ReportDataRepository reportDataRepository;

    private final DataService dataService;


    //  Вивід статистики по  проміжку дат
    @PostMapping("/searchByRange")
    public ResponseEntity<?> findByDateRange(@RequestBody @Valid SearchDateRangeDTO dateDTO, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation error(s): ");
            result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
            log.error(errorMessage.toString());
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }
        List<SalesAndTrafficByDateDTO> byDateIn = dataService.findByDateBetween(dateDTO.getStartDate(), dateDTO.getEndDate());
        log.info("Search by dates {} - {}", dateDTO.getStartDate(), dateDTO.getEndDate());
        return ResponseEntity.ok(byDateIn);
    }

    //  Вивід статистики по одній даті
    @PostMapping("/searchByDate")
    public ResponseEntity<?> findByDate(@RequestBody @Valid SearchDateDTO dateDTO, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation error(s): ");
            result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
            log.error(errorMessage.toString());
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }
        List<SalesAndTrafficByDateDTO> byDateIn = dataService.findByDate(dateDTO.getDate());
        log.info("Search by date {}", dateDTO.getDate());
        return ResponseEntity.ok(byDateIn);
    }

    // Вивід сумарної статистики по всім датам
    @GetMapping
    public ResponseEntity<?> findAllByDate() {
        List<SalesAndTrafficByDateDTO> byDate = dataService.findAllByDate();
        log.info("Search all by date ");
        return ResponseEntity.ok(byDate);
    }


}
