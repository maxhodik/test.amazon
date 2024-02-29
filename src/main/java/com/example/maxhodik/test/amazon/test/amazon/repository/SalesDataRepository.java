package com.example.maxhodik.test.amazon.test.amazon.repository;

import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByDate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SalesDataRepository extends MongoRepository<SalesAndTrafficByDate, String> {

    List<SalesAndTrafficByDate> findByDate(String date);

    List<SalesAndTrafficByDate> findAll();

}

