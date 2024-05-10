package com.tech.springboot.service.impl;

import com.tech.springboot.exception.BusinessException;
import com.tech.springboot.lending.model.AuthRequestDto;
import com.tech.springboot.lending.model.AuthResponseDto;
import com.tech.springboot.model.entity.User;
import com.tech.springboot.repository.TokenRepository;
import com.tech.springboot.service.AuthService;
import com.tech.springboot.service.TokenStore;
import com.tech.springboot.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Slf4j
class AuthImplTest {

    @Autowired
    private AuthImpl auth;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenAuthenticate_thenSuccess() {
        AuthRequestDto authRequestDto = AuthRequestDto.builder()
                .username("user")
                .password("user")
                .build();

        AuthResponseDto authResponseDto = auth.authenticate(authRequestDto);

        assertNotNull(authResponseDto);
    }

    @Test
    void whenAuthenticate_thenFail() {
        AuthRequestDto authRequestDto = AuthRequestDto.builder()
                .username("user")
                .password("password")
                .build();
        AuthResponseDto authResponseDto = auth.authenticate(authRequestDto);
        assertNull(authResponseDto);

    }

    @Test
    void refreshToken() {
    }
}