package com.tech.springboot.controller;

import com.tech.springboot.dto.UserResponseDTO;
import com.tech.springboot.dto.base.RestResponseWrapper;
import com.tech.springboot.entity.User;
import com.tech.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public RestResponseWrapper<List<UserResponseDTO>> getAllUsers() {
        return new RestResponseWrapper<>(userService.getAllUsers());
    }

}
