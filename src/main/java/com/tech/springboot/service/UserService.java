package com.tech.springboot.service;

import com.fpt.training.aio.lending.model.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getUsers();
}
