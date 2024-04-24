package com.tech.springboot.controller;

import com.fpt.training.aio.lending.api.AuthApi;
import com.fpt.training.aio.lending.model.AuthRequestDto;
import com.fpt.training.aio.lending.model.AuthResponseDto;
import com.fpt.training.aio.lending.model.RegisterUserDto;
import com.tech.springboot.service.AuthService;
import com.tech.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController implements AuthApi {
    private final AuthService authService;
    private final UserService userService;

    @Override
    public ResponseEntity<AuthResponseDto> signIn(AuthRequestDto authRequestDto) {
        var response = authService.authenticate(authRequestDto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RegisterUserDto> signUp(RegisterUserDto registerUserDto) {
        var response = userService.register(registerUserDto);
        return ResponseEntity.ok(response);
    }
}
