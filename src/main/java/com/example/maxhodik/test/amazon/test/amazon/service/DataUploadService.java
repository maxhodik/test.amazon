package com.example.maxhodik.test.amazon.test.amazon.service;

import com.example.maxhodik.test.amazon.test.amazon.model.ReportData;
import com.example.maxhodik.test.amazon.test.amazon.model.ReportDataForCompare;
import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByAsin;
import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByDate;
import com.example.maxhodik.test.amazon.test.amazon.repository.ReportDataForCompareRepository;
import com.example.maxhodik.test.amazon.test.amazon.repository.ReportDataRepository;
import com.example.maxhodik.test.amazon.test.amazon.repository.SalesAsinRepository;
import com.example.maxhodik.test.amazon.test.amazon.repository.SalesDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@Component
public class DataUploadService {

    private String STATIC_TEST_REPORT_JSON = "static/test_report.json";
    @Autowired
    private SalesDataRepository salesDataRepository;

    @Autowired
    private SalesAsinRepository salesAsinRepository;
    @Autowired
    private ReportDataRepository reportDataRepository;
    @Autowired
    private ReportDataForCompareRepository compareRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 500000, initialDelay = 3000)
    public void uploadFile() {

        log.info("Attempt to update DB");
        ClassPathResource resource = new ClassPathResource(STATIC_TEST_REPORT_JSON);
        String reportDataForCompare;
        ReportData reportData;
        try {
            reportDataForCompare = getStringDataFromFile(resource);
            reportData = objectMapper.readValue(reportDataForCompare, ReportData.class);
        } catch (IOException e) {
            log.error("Can't read JSON from {}", STATIC_TEST_REPORT_JSON);
            throw new RuntimeException(e);
        }
        if (reportData == null) {
            log.error("test_report.json is empty");
            return;
        }

        if (isUpdateNeeded(reportDataForCompare)) {
            cleanDB();
            compareRepository.save(new ReportDataForCompare(reportDataForCompare));
            ReportData savedReportData = saveReportData(reportData);
            saveSalesDate(savedReportData);
            saveSalesAsin(savedReportData);
            log.info("DB updated");
        } else {
            log.info("Updating DB no need");
        }
    }

    private String getStringDataFromFile(ClassPathResource resource) throws IOException {
        String reportDataForCompare;
        try (InputStream inputStream = resource.getInputStream()) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                reportDataForCompare = bufferedReader.lines().collect(Collectors.joining("\n"));
            }
        }
        return reportDataForCompare;
    }

    private void cleanDB() {
        compareRepository.deleteAll();
        reportDataRepository.deleteAll();
        salesDataRepository.deleteAll();
        salesAsinRepository.deleteAll();
        log.info("DB cleaned");
    }

    private boolean isUpdateNeeded(String reportData) {

        List<ReportDataForCompare> all = compareRepository.findAll();
        if (CollectionUtils.isEmpty(all)) {
            return true;
        }
        String reportDataFromBd = all.get(0).getReportData();
        return reportDataFromBd == null || !reportDataFromBd.equals(reportData);
    }

    private void saveSalesDate(ReportData reportData) {
        List<SalesAndTrafficByDate> salesAndTrafficByDateList = reportData.getSalesAndTrafficByDate();
        salesDataRepository.saveAll(salesAndTrafficByDateList);
        log.info("Sales by date uploaded");
    }

    private void saveSalesAsin(ReportData reportData) {
        List<SalesAndTrafficByAsin> salesAndTrafficByAsinList =
                reportData.getSalesAndTrafficByAsin();
        salesAsinRepository.saveAll(salesAndTrafficByAsinList);
        log.info("Sales by ASIN uploaded");
    }

    private ReportData saveReportData(ReportData reportData) {
        ReportData savedReportData = reportDataRepository.save(reportData);
        log.info("new report Date uploaded");
        return savedReportData;
    }
}


