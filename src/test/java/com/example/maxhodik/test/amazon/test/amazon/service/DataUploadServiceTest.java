package com.example.maxhodik.test.amazon.test.amazon.service;

import com.example.maxhodik.test.amazon.test.amazon.TestUtils;
import com.example.maxhodik.test.amazon.test.amazon.model.ReportData;
import com.example.maxhodik.test.amazon.test.amazon.model.ReportDataForCompare;
import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByAsin;
import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByDate;
import com.example.maxhodik.test.amazon.test.amazon.repository.ReportDataForCompareRepository;
import com.example.maxhodik.test.amazon.test.amazon.repository.ReportDataRepository;
import com.example.maxhodik.test.amazon.test.amazon.repository.SalesAsinRepository;
import com.example.maxhodik.test.amazon.test.amazon.repository.SalesDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataUploadServiceTest {
    public static final String TEST_DATA_REPORT_JSON = "test.data.report.json";
    public static final String WRONG_TEST_DATA_REPORT_JSON = "wrong.test.data.report.json";
    Gson gson = new Gson();
    private final String report = TestUtils.readResource(TEST_DATA_REPORT_JSON);
    private final String wrongReport = TestUtils.readResource(WRONG_TEST_DATA_REPORT_JSON);
    private final ReportDataForCompare reportDataForCompare = new ReportDataForCompare(report);
    private final ReportDataForCompare wrongReportDataForCompare = new ReportDataForCompare(wrongReport);
    private final ReportData reportData = gson.fromJson(report, ReportData.class);

    private final String salesByAsin = TestUtils.readResource("sale.and.traffic.by.asin.json");
    private final SalesAndTrafficByAsin salesAndTrafficByAsin = gson.fromJson(salesByAsin, SalesAndTrafficByAsin.class);
    private final String salesByDate = TestUtils.readResource("sale.and.traffic.by.asin.json");

    private final SalesAndTrafficByDate salesAndTrafficByDate = gson.fromJson(salesByDate, SalesAndTrafficByDate.class);
    private final List<SalesAndTrafficByDate> salesAndTrafficByDateList = List.of(salesAndTrafficByDate);
    @Mock
    private SalesDataRepository salesDataRepository;
    @Mock
    private SalesAsinRepository salesAsinRepository;
    @Mock
    private ReportDataRepository reportDataRepository;
    @Mock
    private ReportDataForCompareRepository compareRepository;

    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    DataUploadService uploadService;

    @BeforeEach
    void setUp() {

        ReflectionTestUtils.setField(uploadService, "objectMapper", objectMapper);
        ReflectionTestUtils.setField(uploadService, "STATIC_TEST_REPORT_JSON", TEST_DATA_REPORT_JSON);

    }

    @Test
    void uploadFile_ShouldSaveDB() throws IOException {
        // GIVEN
        when(objectMapper.readValue(anyString(), eq(ReportData.class))).thenReturn(reportData);
        when(compareRepository.findAll()).thenReturn(null);
        when(compareRepository.save(any(ReportDataForCompare.class))).thenReturn(null);
        when(reportDataRepository.save(any(ReportData.class))).thenReturn(reportData);
        // WHEN
        uploadService.uploadFile();
        //THEN
        verify(salesDataRepository).saveAll(reportData.getSalesAndTrafficByDate());
        verify(salesAsinRepository).saveAll(reportData.getSalesAndTrafficByAsin());
        verify(reportDataRepository).save(reportData);
        verify(compareRepository).deleteAll();
        verify(reportDataRepository).deleteAll();
        verify(salesDataRepository).deleteAll();
        verify(salesAsinRepository).deleteAll();
    }

    @Test
    void uploadFile_ShouldSkippSaving() throws IOException {
        // GIVEN
        when(objectMapper.readValue(anyString(), eq(ReportData.class))).thenReturn(reportData);
        when(compareRepository.findAll()).thenReturn(List.of(reportDataForCompare));
        // WHEN
        uploadService.uploadFile();
        //THEN
        verify(salesDataRepository, never()).saveAll(reportData.getSalesAndTrafficByDate());
        verify(salesAsinRepository, never()).saveAll(reportData.getSalesAndTrafficByAsin());
        verify(reportDataRepository, never()).save(reportData);
        verify(compareRepository, never()).deleteAll();
        verify(reportDataRepository, never()).deleteAll();
        verify(salesDataRepository, never()).deleteAll();
        verify(salesAsinRepository, never()).deleteAll();
    }

    @Test
    void uploadFile_ShouldSave_WhenListIsEmpty() throws IOException {
        // GIVEN
        when(objectMapper.readValue(anyString(), eq(ReportData.class))).thenReturn(reportData);
        when(compareRepository.findAll()).thenReturn(List.of());
        when(compareRepository.save(any(ReportDataForCompare.class))).thenReturn(null);
        when(reportDataRepository.save(any(ReportData.class))).thenReturn(reportData);
        // WHEN
        uploadService.uploadFile();
        // THEN
        verify(salesDataRepository).saveAll(reportData.getSalesAndTrafficByDate());
        verify(salesAsinRepository).saveAll(reportData.getSalesAndTrafficByAsin());
        verify(reportDataRepository).save(reportData);
        verify(compareRepository).deleteAll();
        verify(reportDataRepository).deleteAll();
        verify(salesDataRepository).deleteAll();
        verify(salesAsinRepository).deleteAll();
    }

    @Test
    void uploadFile_ShouldSave_WhenNotEqual() throws IOException {
        // GIVEN
        when(objectMapper.readValue(anyString(), eq(ReportData.class))).thenReturn(reportData);
        when(compareRepository.findAll()).thenReturn(List.of(wrongReportDataForCompare));
        when(compareRepository.save(any(ReportDataForCompare.class))).thenReturn(null);
        when(reportDataRepository.save(any(ReportData.class))).thenReturn(reportData);
        // WHEN
        uploadService.uploadFile();
        // THEN
        verify(salesDataRepository).saveAll(reportData.getSalesAndTrafficByDate());
        verify(salesAsinRepository).saveAll(reportData.getSalesAndTrafficByAsin());
        verify(reportDataRepository).save(reportData);
        verify(compareRepository).deleteAll();
        verify(reportDataRepository).deleteAll();
        verify(salesDataRepository).deleteAll();
        verify(salesAsinRepository).deleteAll();
    }


}
