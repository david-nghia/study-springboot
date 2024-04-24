package com.tech.springboot.service;

import com.fpt.training.aio.lending.model.*;

import java.util.List;
import java.util.UUID;

public interface UserService {

    RegisterUserDto register(RegisterUserDto registerUserDto);
    UserResponseDto addUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getUsers();

    UserResponseDto updateUserById(UUID uuid, UserRequestDto userRequestDto);

    UserResponseDto getUserById(UUID uuid);

    void deleteUserById(UUID uuid);

    List<RoleResponseDto> assignRoleToUser(UserRoleRequestDto userRoleRequestDto);
}
