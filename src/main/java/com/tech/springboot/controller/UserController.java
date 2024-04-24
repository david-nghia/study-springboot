package com.tech.springboot.controller;

import com.fpt.training.aio.lending.api.UserApi;
import com.fpt.training.aio.lending.model.RoleResponseDto;
import com.fpt.training.aio.lending.model.UserRequestDto;
import com.fpt.training.aio.lending.model.UserResponseDto;
import com.fpt.training.aio.lending.model.UserRoleRequestDto;
import com.tech.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    public ResponseEntity<UserResponseDto> addUser(UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.addUser(userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @Override
    public ResponseEntity<List<RoleResponseDto>> assignRolesToUser(UserRoleRequestDto userRoleRequestDto) {
        List<RoleResponseDto> roles = userService.assignRoleToUser(userRoleRequestDto);
        return ResponseEntity.ok(roles);
    }

    @Override
    public ResponseEntity<Void> deleteUserById(UUID id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<UserResponseDto> getUserById(UUID id) {
        UserResponseDto userResponseDto = userService.getUserById(id);
        return ResponseEntity.ok(userResponseDto);
    }

    @Override
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        List<UserResponseDto> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

//    @Override
//    public ResponseEntity<UserResponseDto> updateUserById(UUID id, UserRequestDto userRequestDto) {
//        UserResponseDto userResponseDto = userService.updateUserById(id, userRequestDto);
//        return ResponseEntity.ok(userResponseDto);
//    }
}
