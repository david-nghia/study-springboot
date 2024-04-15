package com.tech.springboot.controller;

import com.tech.springboot.dto.ListUserResponseDTO;
import com.tech.springboot.dto.base.RestResponseWrapper;
import com.tech.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('User')")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    @Operation(
            description = "Get endpoint for user",
            summary = "This is a summary for user get endpoint",
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

    @GetMapping()
    public RestResponseWrapper<ListUserResponseDTO> getAllUsers(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "5") int limit) {
        return new RestResponseWrapper<>(userService.getAllUsers(offset, limit));
    }

}
