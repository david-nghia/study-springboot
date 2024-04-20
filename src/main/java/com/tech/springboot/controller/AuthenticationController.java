package com.tech.springboot.controller;

import com.fpt.training.aio.lending.api.AuthApi;
import com.fpt.training.aio.lending.model.AuthRequest;
import com.fpt.training.aio.lending.model.AuthResponse;
import com.fpt.training.aio.lending.model.RefreshTokenRequest;
import com.tech.springboot.until.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthApi {
    private final JwtUtil jwtTokenUtil;

    @Override
    public ResponseEntity<AuthResponse> authenticate(AuthRequest authRequest) {
        final String jwt = jwtTokenUtil.genAccessToken(authRequest.getUsername());
        final String refreshToken = jwtTokenUtil.genRefreshToken(authRequest.getUsername());
        AuthResponse response = new AuthResponse(jwt, refreshToken);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<AuthResponse> refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String username = jwtTokenUtil.getUsernameFromToken(refreshTokenRequest.getRefreshToken());
        if (jwtTokenUtil.validateToken(refreshTokenRequest.getRefreshToken())) {
            final String newJwt = jwtTokenUtil.genAccessToken(username);
            final String refreshToken = jwtTokenUtil.genRefreshToken(username);
            AuthResponse response = new AuthResponse(newJwt, refreshToken);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(null);
    }

}
