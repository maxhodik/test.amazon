package com.example.maxhodik.test.amazon.test.amazon.service;

import com.example.maxhodik.test.amazon.test.amazon.mapper.SalesAndTrafficByAsinMapper;
import com.example.maxhodik.test.amazon.test.amazon.repository.SalesAsinRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AsinServiceImplTest {

    @Mock
    private SalesAsinRepository asinRepository;
    @Mock
    private SalesAndTrafficByAsinMapper mapper;
    @InjectMocks
    private AsinServiceImpl asinService;

    @Test
    void findByAsins() {
    }

    @Test
    void findAllByAsins() {
    }

    @Test
    void getAsinRepository() {
    }

    @Test
    void getMapper() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}