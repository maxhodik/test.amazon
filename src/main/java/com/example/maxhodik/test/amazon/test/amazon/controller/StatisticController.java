package com.example.maxhodik.test.amazon.test.amazon.controller;

import com.example.maxhodik.test.amazon.test.amazon.dto.ReportDataAsinDTO;
import com.example.maxhodik.test.amazon.test.amazon.dto.ReportDataDateDTO;
import com.example.maxhodik.test.amazon.test.amazon.dto.SearchDateDTO;
import com.example.maxhodik.test.amazon.test.amazon.dto.SearchDateRangeDTO;
import com.example.maxhodik.test.amazon.test.amazon.model.ReportData;
import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByAsin;
import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByDate;
import com.example.maxhodik.test.amazon.test.amazon.repository.ReportDataRepository;
import com.example.maxhodik.test.amazon.test.amazon.service.AsinService;
import com.example.maxhodik.test.amazon.test.amazon.service.DataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic")
public class StatisticController {

    private final ReportDataRepository reportDataRepository;
    private final AsinService asinService;
    private final DataService dataService;



    //  Вивід статистики по  проміжку дат
    @GetMapping("/by_dates")
    public ResponseEntity<?> findByDateRange (@RequestBody @Valid SearchDateRangeDTO dateDTO, BindingResult result) {
        ReportDataDateDTO reportData = new ReportDataDateDTO();
        if (result.hasErrors()) {

            StringBuilder errorMessage = new StringBuilder("Validation error(s): ");
            result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
            log.error(errorMessage.toString());
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }
        List<SalesAndTrafficByDate> byDateIn = dataService.findByDateBetween(dateDTO.getStartDate(), dateDTO.getEndDate());


        log.info("Search by dates {} - {}", dateDTO.getStartDate(), dateDTO.getEndDate());
        return ResponseEntity.ok(byDateIn);
    }
    //  Вивід статистики по одній даті
    @GetMapping("/by_date")
    public ResponseEntity<?> findByDate(@RequestBody @Valid SearchDateDTO dateDTO, BindingResult result) {
        ReportDataDateDTO reportData = new ReportDataDateDTO();
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation error(s): ");
            result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
            log.error(errorMessage.toString());
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }
        List<SalesAndTrafficByDate> byDateIn = dataService.findByDate(dateDTO.getDate());


        log.info("Search by date {}", dateDTO.getDate());
        return ResponseEntity.ok(byDateIn);
    }
    @GetMapping("/all_date")
    public ResponseEntity<?> findByDate() {
        ReportDataDateDTO reportData = new ReportDataDateDTO();

        List<SalesAndTrafficByDate> byDate = dataService.findAllByDate();

        log.info("Search all by date ");
        return ResponseEntity.ok(byDate);
    }


    @GetMapping()
    public ResponseEntity<List<ReportData>> getAll() {
        List<ReportData> all = reportDataRepository.findAll();
        return ResponseEntity.ok(all);
    }



    @GetMapping("/by_asin")
    public ResponseEntity<?> findByAsin(@RequestBody List<String> asins) {
        ReportDataAsinDTO reportData = new ReportDataAsinDTO();

        List<SalesAndTrafficByAsin> byAsinIn = asinService.findByAsins(asins);

        log.info("Search by asins {}", asins);
        return ResponseEntity.ok(byAsinIn);
    }

    @GetMapping("/all_asin")
    public ResponseEntity<?> findAllByAsin() {
        ReportDataAsinDTO reportData = new ReportDataAsinDTO();

        List<SalesAndTrafficByAsin> byAsin = asinService.findAllByAsins();

        log.info("Search all by asins");
        return ResponseEntity.ok(byAsin);
    }
}
