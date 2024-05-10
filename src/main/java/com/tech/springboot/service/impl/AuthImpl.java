package com.tech.springboot.service.impl;

import com.tech.springboot.exception.BusinessException;
import com.tech.springboot.lending.model.AuthRequestDto;
import com.tech.springboot.lending.model.AuthResponseDto;
import com.tech.springboot.lending.model.RefreshTokenRequestDto;
import com.tech.springboot.lending.model.UserResponseDto;
import com.tech.springboot.model.entity.Token;
import com.tech.springboot.model.entity.User;
import com.tech.springboot.enums.TokenTypeEnum;
import com.tech.springboot.mapper.UserMapper;
import com.tech.springboot.repository.TokenRepository;
import com.tech.springboot.service.AuthService;
import com.tech.springboot.service.TokenStore;
import com.tech.springboot.utils.DateTimeUtils;
import com.tech.springboot.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthImpl implements AuthService {
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final UserSpringService userSpringService;
    private final TokenRepository tokenRepository;
    private final TokenStore tokenStore;


    @Override
    public AuthResponseDto authenticate(AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getUsername(),
                        authRequestDto.getPassword()
                )
        );

//        tokenStore.storeToken("token_key" ,authentication);

        UserDetails userInfo = userSpringService.loadUserByUsername(authentication.getName());
        return buildAuthResponse(userInfo);
    }

    @Override
    public AuthResponseDto refreshToken(RefreshTokenRequestDto request) {
        log.info("get refresh token");
        String refreshToken = request.getRefreshToken();
        log.info("get refresh token successful");
        if (!StringUtils.hasText(refreshToken)) {
            throw new BusinessException("Refresh token is null");
        }
        log.info("refresh token is not null");

//        if (Boolean.FALSE.equals(jwtTokenUtils.validateToken(refreshToken))) {
//            throw new BusinessException("Refresh token expired");
//        }

        log.info("Refresh token is not expired");

        // find in database
        Optional<Token> tokenOptional = tokenRepository.findTokenByRefreshTokenAndRevoked(
                refreshToken, Boolean.FALSE);
        log.info("find token in database successful");
//        Optional<Token> tokenOptional = tokenRepository.findTokenByRefreshToken(refreshToken);

        if (tokenOptional.isEmpty()) {
            log.info("Token is empty ---");
            throw new BusinessException("Not found refresh token active");
        }else{
            log.info("Token is not empty: {}", tokenOptional.get());
        }

        String username = jwtTokenUtils.getUsernameFromToken(refreshToken);
        log.info("get username from refresh token successful");

        // set revoke for old token
        Token token = tokenOptional.get();
        token.setRevoked(Boolean.TRUE);
        token.setExpired(Boolean.TRUE);
        tokenRepository.save(token);

        UserDetails userInfo = userSpringService.loadUserByUsername(username);
        return buildAuthResponse(userInfo);
    }

    private AuthResponseDto buildAuthResponse(UserDetails userInfo) {
        User userEntity = (User) userInfo;

        //check status user
        var accessToken = jwtTokenUtils.genAccessToken(userInfo);
        var refreshToken = jwtTokenUtils.genRefreshToken(userInfo);


        // save session
        Token token = new Token();
        token.setCreatedDate(OffsetDateTime.now());
        token.setCreatedBy("system");
        token.setModifiedDate(OffsetDateTime.now());
        token.setModifiedBy("system");
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setUser(userEntity);
        tokenRepository.save(token);

        UserResponseDto userResponseDto = UserMapper.INSTANCE.toDTO(userEntity);

        Date date = jwtTokenUtils.getExpirationDateFromToken(accessToken);
        OffsetDateTime expiresIn = DateTimeUtils.convertDateToDateTime(date);

        return AuthResponseDto.builder()
                .accessToken(accessToken)
                .tokenType(TokenTypeEnum.BEARER.name()).user(userResponseDto)
                .expiresOn(expiresIn.toString())
                .build();

    }


}
