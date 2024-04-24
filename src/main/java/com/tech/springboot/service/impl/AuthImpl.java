package com.tech.springboot.service.impl;

import com.fpt.training.aio.lending.model.AuthRequestDto;
import com.fpt.training.aio.lending.model.AuthResponseDto;
import com.fpt.training.aio.lending.model.RefreshTokenDto;
import com.fpt.training.aio.lending.model.UserResponseDto;
import com.tech.springboot.entity.Token;
import com.tech.springboot.entity.User;
import com.tech.springboot.enums.TokenTypeEnum;
import com.tech.springboot.mapper.UserMapper;
import com.tech.springboot.repository.TokenRepository;
import com.tech.springboot.service.AuthService;
import com.tech.springboot.until.DateTimeUtils;
import com.tech.springboot.until.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserSpringService userSpringService;
    private final TokenRepository tokenRepository;


    @Override
    public AuthResponseDto authenticate(AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getUsername(),
                        authRequestDto.getPassword()
                )
        );

        UserDetails userInfo = userSpringService.loadUserByUsername(authentication.getName());
        return buildAuthResponse(userInfo);
    }

    private AuthResponseDto buildAuthResponse(UserDetails userInfo) {
        User userEntity = (User) userInfo;

        var accessToken = jwtUtil.genAccessToken(userInfo);
        var refreshToken = jwtUtil.genRefreshToken(userInfo);

        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setUser(userEntity);
        tokenRepository.save(token);

        UserResponseDto userResponseDto = UserMapper.INSTANCE.toDTO(userEntity);

        Date date = jwtUtil.getExpirationDateFromToken(accessToken);
        OffsetDateTime expiresIn = DateTimeUtils.convertDateToDateTime(date);

        return AuthResponseDto.builder()
                .accessToken(accessToken)
                .tokenType(TokenTypeEnum.BEARER.name()).user(userResponseDto)
                .expiresOn(expiresIn.toString())
                .build();

    }

    @Override
    public AuthResponseDto refreshToken(RefreshTokenDto refreshTokenDto) {
        return null;
    }
}
