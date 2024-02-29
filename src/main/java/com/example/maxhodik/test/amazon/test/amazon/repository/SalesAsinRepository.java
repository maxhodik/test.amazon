package com.example.maxhodik.test.amazon.test.amazon.repository;

import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByAsin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SalesAsinRepository extends MongoRepository<SalesAndTrafficByAsin, String> {

    List<SalesAndTrafficByAsin> findByParentAsinIn(List<String> asins);
}
