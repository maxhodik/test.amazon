//package com.example.maxhodik.test.amazon.test.amazon.auth.jwt;
//
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//@Data
//@Configuration
//public class JwtConfig {
//
//    @Value("${jwt.secret}")
//    private String secretKey;
//    @Value("${jwt.header}")
//    private String authorizationHeader;
//    @Value("${jwt.expiration}")
//    private long validityInHours;
//    @Value("${jwt.refresh.expiration}")
//    private long refreshValidityInDays;
//    @Value("${jwt.prefix}")
//    private String tokenPrefix;
//    @Value("${jwt.service.expiration.min}")
//    private long serviceTokenValidityInMins;
//
//}