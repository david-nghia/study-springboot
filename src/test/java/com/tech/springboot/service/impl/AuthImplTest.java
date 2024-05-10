package com.tech.springboot.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.tech.springboot.lending.model.AuthRequestDto;
import com.tech.springboot.lending.model.AuthResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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