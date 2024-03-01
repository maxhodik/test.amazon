package com.example.maxhodik.test.amazon.test.amazon.mapper;

import com.example.maxhodik.test.amazon.test.amazon.dto.SalesAndTrafficByAsinDTO;
import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByAsin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalesAndTrafficByAsinMapper {

    SalesAndTrafficByAsinDTO mapToSalesDTO(SalesAndTrafficByAsin salesAndTrafficByAsin);
}
