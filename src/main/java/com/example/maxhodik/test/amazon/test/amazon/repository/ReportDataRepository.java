package com.example.maxhodik.test.amazon.test.amazon.repository;

import com.example.maxhodik.test.amazon.test.amazon.model.ReportData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface ReportDataRepository extends MongoRepository<ReportData, String> {
   @Query("{'SalesAndTrafficByDate.date': ?0}")
    List<ReportData> findBySalesAndTrafficByDate_ByDate(String date);
    @Query("{'parentAsin' :{'$in':[?0]}}")
    List<ReportData> findAllSalesAndTrafficAsin_ByParentAsin(List<String> asins);
}

