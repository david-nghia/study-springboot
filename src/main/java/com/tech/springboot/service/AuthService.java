package com.tech.springboot.service;

import com.fpt.training.aio.lending.model.AuthRequestDto;
import com.fpt.training.aio.lending.model.AuthResponseDto;
import com.fpt.training.aio.lending.model.RefreshTokenDto;
import com.fpt.training.aio.lending.model.RegisterUserDto;

public interface AuthService {
    AuthResponseDto authenticate(AuthRequestDto authRequestDto);

    AuthResponseDto refreshToken(RefreshTokenDto refreshTokenDto);
}
