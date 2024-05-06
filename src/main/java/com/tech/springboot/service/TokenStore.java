package com.tech.springboot.service;

import org.springframework.security.core.Authentication;

public interface TokenStore {

    void storeToken(String token, Authentication authentication);


    void removeToken(Authentication authentication);

    boolean isTokenExpired(String token);

    Boolean currentToken(String token);
}
