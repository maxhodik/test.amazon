package com.example.maxhodik.test.amazon.test.amazon.repository;

import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SalesDateCustomRepositoryImpl implements SalesDateCustomRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public List<SalesAndTrafficByDate> findByDateBetweenInclusive(String startDate, String endDate) {
        Criteria criteria = Criteria.where("date").gte(startDate).andOperator(Criteria.where("date").lte(endDate));
        Query query = new Query(criteria);
        return mongoTemplate.find(query, SalesAndTrafficByDate.class);
    }
}
