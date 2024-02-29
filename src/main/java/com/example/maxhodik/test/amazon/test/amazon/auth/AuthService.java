package com.example.maxhodik.test.amazon.test.amazon.auth;

import com.example.maxhodik.test.amazon.test.amazon.model.User;
import org.springframework.security.core.Authentication;

public interface AuthService {
    Authentication getAuthentication(String token);

    User getUserFromAuth();

    User getUserFromAuth(Authentication authentication);

    String getUsername();

}
