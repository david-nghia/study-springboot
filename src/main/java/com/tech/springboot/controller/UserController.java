package com.tech.springboot.controller;

import com.fpt.training.aio.lending.api.UserApi;
import com.fpt.training.aio.lending.model.UserResponseDto;
import com.tech.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController implements UserApi {
    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        List<UserResponseDto> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }
}
