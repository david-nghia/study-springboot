package com.tech.springboot.service.impl;

import com.tech.springboot.service.TokenStore;
import com.tech.springboot.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RedisTokenStore implements TokenStore {

    private final String TOKEN_PREFIX = "token:";
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Override
    public void storeToken(String token, Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        redisTemplate.opsForValue().set(TOKEN_PREFIX + username, token);
    }

    @Override
    public void removeToken(Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        redisTemplate.delete(TOKEN_PREFIX + username);
    }

    @Override
    public boolean isTokenExpired(String token) {
        String username = jwtTokenUtils.getUsernameFromToken(token);
        Date expiration = jwtTokenUtils.getExpirationDateFromToken(token);
        Date now = new Date();
        return now.after(expiration)
            || redisTemplate.opsForValue().get(TOKEN_PREFIX + username) == null;
    }

    @Override
    public Boolean currentToken(String token) {
        String username = jwtTokenUtils.getUsernameFromToken(token);
        String currentToken = (String) redisTemplate.opsForValue().get(TOKEN_PREFIX + username);
        return token.equals(currentToken);
    }

}