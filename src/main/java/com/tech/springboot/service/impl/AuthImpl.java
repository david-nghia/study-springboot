package com.tech.springboot.service.impl;

import com.tech.springboot.enums.TokenTypeEnum;
import com.tech.springboot.exception.BusinessException;
import com.tech.springboot.lending.model.AuthRequestDto;
import com.tech.springboot.lending.model.AuthResponseDto;
import com.tech.springboot.lending.model.RefreshTokenRequestDto;
import com.tech.springboot.lending.model.UserResponseDto;
import com.tech.springboot.mapper.UserMapper;
import com.tech.springboot.model.entity.User;
import com.tech.springboot.service.AuthService;
import com.tech.springboot.service.TokenStore;
import com.tech.springboot.utils.DateTimeUtils;
import com.tech.springboot.utils.JwtTokenUtils;
import java.time.OffsetDateTime;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthImpl implements AuthService {
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final UserSpringService userSpringService;
    private final TokenStore tokenStore;


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

    @Override
    public AuthResponseDto refreshToken(RefreshTokenRequestDto request) {
        String refreshToken = request.getRefreshToken();
        if (!StringUtils.hasText(refreshToken)) {
            throw new BusinessException("Refresh token is null");
        }

        // TODO: verify token in redis

        String username = jwtTokenUtils.getUsernameFromToken(refreshToken);
        log.info("get username from refresh token successful");

        UserDetails userInfo = userSpringService.loadUserByUsername(username);
        return buildAuthResponse(userInfo);
    }

    private AuthResponseDto buildAuthResponse(UserDetails userInfo) {
        User userEntity = (User) userInfo;

        //check status user
        var accessToken = jwtTokenUtils.genAccessToken(userInfo);
        var refreshToken = jwtTokenUtils.genRefreshToken(userInfo);

        // TODO: save token to redis

        UserResponseDto userResponseDto = UserMapper.INSTANCE.toDTO(userEntity);
        Date date = jwtTokenUtils.getExpirationDateFromToken(accessToken);
        OffsetDateTime expiresIn = DateTimeUtils.convertDateToDateTime(date);

        return AuthResponseDto.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .tokenType(TokenTypeEnum.BEARER.name())
            .user(userResponseDto)
            .expiresOn(expiresIn.toString())
            .build();

    }


}
