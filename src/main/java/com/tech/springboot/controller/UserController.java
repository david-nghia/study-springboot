package com.tech.springboot.controller;

import com.tech.springboot.dto.ListUserResponseDTO;
import com.tech.springboot.dto.base.RestResponseWrapper;
import com.tech.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public RestResponseWrapper<ListUserResponseDTO> getAllUsers(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "5") int limit) {
        return new RestResponseWrapper<>(userService.getAllUsers(offset, limit));
    }

}
