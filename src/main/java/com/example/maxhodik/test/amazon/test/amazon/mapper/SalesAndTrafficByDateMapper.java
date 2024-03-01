package com.example.maxhodik.test.amazon.test.amazon.mapper;

import com.example.maxhodik.test.amazon.test.amazon.dto.SalesAndTrafficByDateDTO;
import com.example.maxhodik.test.amazon.test.amazon.model.SalesAndTrafficByDate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalesAndTrafficByDateMapper {

    SalesAndTrafficByDateDTO mapToSalesDTO(SalesAndTrafficByDate salesAndTrafficByDate);


}