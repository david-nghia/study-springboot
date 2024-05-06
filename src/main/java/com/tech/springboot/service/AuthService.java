package com.tech.springboot.service;

import com.tech.springboot.lending.model.AuthRequestDto;
import com.tech.springboot.lending.model.AuthResponseDto;
import com.tech.springboot.lending.model.RefreshTokenRequestDto;
import com.tech.springboot.lending.model.RegisterUserDto;

public interface AuthService {
    AuthResponseDto authenticate(AuthRequestDto authRequestDto);

    AuthResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);
}
