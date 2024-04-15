package com.tech.springboot.controller;

import com.tech.springboot.dto.base.RestResponseWrapper;
import com.tech.springboot.model.AuthRequest;
import com.tech.springboot.model.AuthResponse;
import com.tech.springboot.model.RefreshTokenRequest;
import com.tech.springboot.until.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class AuthenticationController {
    private final JwtUtil jwtTokenUtil;

    @Operation(
            description = "Post endpoint for authentication",
            summary = "This is a summary for authentication post endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "UnAuthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Internal server error",
                            responseCode = "500"
                    ),
            }
    )

    @PostMapping("/authenticate")
    public RestResponseWrapper<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        final String jwt = jwtTokenUtil.genAccessToken(authRequest.getUsername());
        final String refreshToken = jwtTokenUtil.genRefreshToken(authRequest.getUsername());
        return new RestResponseWrapper<>(new AuthResponse(jwt, refreshToken));
    }

    @Operation(
            description = "Post endpoint for refresh",
            summary = "This is a summary for refresh post endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "UnAuthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Internal server error",
                            responseCode = "500"
                    ),
            }
    )

    @PostMapping("/refresh")
    public RestResponseWrapper<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String username = jwtTokenUtil.getUsernameFromToken(refreshTokenRequest.getRefreshToken());
        if (jwtTokenUtil.validateToken(refreshTokenRequest.getRefreshToken())) {
            final String newJwt = jwtTokenUtil.genAccessToken(username);
            final String refreshToken = jwtTokenUtil.genRefreshToken(username);
            return new RestResponseWrapper<>(new AuthResponse(newJwt, refreshToken));
        }
        return new RestResponseWrapper<>("Invalid refresh token", HttpStatus.FORBIDDEN);
    }
}
