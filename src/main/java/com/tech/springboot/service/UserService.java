package com.tech.springboot.service;

import com.tech.springboot.lending.model.*;

import java.util.List;
import java.util.UUID;

public interface UserService {

    RegisterUserDto register(RegisterUserDto registerUserDto);

    List<UserResponseDto> getUsers();

    UserResponseDto updateUserById(UUID uuid, UserRequestDto userRequestDto);

    UserResponseDto getUserById(UUID uuid);

    void deleteUserById(UUID uuid);

    List<RoleResponseDto> assignRoleToUser(UserRoleRequestDto userRoleRequestDto);
    List<UserResponseDto> searchUser(Integer offset, Integer limit, List<String> sortBy,List<String> search);
}
