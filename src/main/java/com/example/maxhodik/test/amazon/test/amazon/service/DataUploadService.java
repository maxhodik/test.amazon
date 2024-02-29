package com.example.maxhodik.test.amazon.test.amazon.service;

import com.example.maxhodik.test.amazon.test.amazon.model.ReportData;
import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByAsin;
import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByDate;
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

import java.io.IOException;
import java.util.List;

@Log4j2
@Service
@Component
public class DataUploadService {

    @Autowired
    private SalesDataRepository salesDataRepository;

    @Autowired
    private SalesAsinRepository salesAsinRepository;
    @Autowired
    private ReportDataRepository reportDataRepository;

    @Autowired
    private ObjectMapper objectMapper;

//    @Scheduled(fixedDelay = 500000, initialDelay = 3000)
    public void uploadFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("static/test_report.json");
        ReportData reportData = saveReportData(objectMapper, resource);
        saveSalesDate(reportData);
        saveSalesAsin(reportData);
    }

    private void saveSalesDate(ReportData reportData) throws IOException {
        List<SalesAndTrafficByDate> salesAndTrafficByDateList = reportData.getSalesAndTrafficByDate();
//                List.of(objectMapper.readValue(resource.getInputStream(), SalesAndTrafficByDate[].class));
        salesDataRepository.saveAll(salesAndTrafficByDateList);
        log.info("Sales by date uploaded");
    }

    private void saveSalesAsin(ReportData reportData) throws IOException {
        List<SalesAndTrafficByAsin> salesAndTrafficByAsinList =
                reportData.getSalesAndTrafficByAsin();
        salesAsinRepository.saveAll(salesAndTrafficByAsinList);
        log.info("Sales by ASIN uploaded");
    }

    private ReportData saveReportData(ObjectMapper objectMapper, ClassPathResource resource) throws IOException {
        ReportData reportData = objectMapper.readValue(resource.getInputStream(), ReportData.class);
        ReportData savedReportData = reportDataRepository.save(reportData);

        log.info("new report Date uploaded");
        return savedReportData;
    }
}


