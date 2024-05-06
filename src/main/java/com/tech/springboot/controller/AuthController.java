package com.tech.springboot.controller;

import com.tech.springboot.lending.api.AuthApi;
import com.tech.springboot.lending.model.AuthRequestDto;
import com.tech.springboot.lending.model.AuthResponseDto;
import com.tech.springboot.lending.model.RefreshTokenRequestDto;
import com.tech.springboot.lending.model.RegisterUserDto;
import com.tech.springboot.service.AuthService;
import com.tech.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController implements AuthApi {
    private final AuthService authService;
    private final UserService userService;

    @Override
    public ResponseEntity<AuthResponseDto> signIn(AuthRequestDto authRequestDto) {
        log.info("Login request: {}", authRequestDto);
        var response = authService.authenticate(authRequestDto);
        log.info("Login successful: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RegisterUserDto> signUp(RegisterUserDto registerUserDto) {
        log.info("Register user {}", registerUserDto.getUsername());
        var response = userService.register(registerUserDto);
        log.info("Registration successful: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<AuthResponseDto> refreshAccessToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        log.info("Refresh access token: {}", refreshTokenRequestDto);
        var response = authService.refreshToken(refreshTokenRequestDto);
        log.info("Login successful: {}", response);
        return ResponseEntity.ok(response);
    }
}
