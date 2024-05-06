package com.tech.springboot.controller;

import com.tech.springboot.lending.api.UserApi;
import com.tech.springboot.lending.model.RoleResponseDto;
import com.tech.springboot.lending.model.UserRequestDto;
import com.tech.springboot.lending.model.UserResponseDto;
import com.tech.springboot.lending.model.UserRoleRequestDto;
import com.tech.springboot.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    public ResponseEntity<List<RoleResponseDto>> assignRolesToUser(UserRoleRequestDto userRoleRequestDto) {
        log.info("Assign roles to user by id: {}", userRoleRequestDto.getUserId());
        List<RoleResponseDto> roles = userService.assignRoleToUser(userRoleRequestDto);
        log.info("Assign roles to user successful");
        return ResponseEntity.ok(roles);
    }

    @Override
    public ResponseEntity<Void> deleteUserById(UUID id) {
        log.info("Delete user by id {}", id);
        userService.deleteUserById(id);
        log.info("Delete user successful");
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<UserResponseDto> getUserById(UUID id) {
        log.info("Get user by id {}", id);
        var response = userService.getUserById(id);
        log.info("Get user successful: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        log.info("Get active users");
        List<UserResponseDto> users = userService.getUsers();
        log.info("Get list of active user successful");
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<UserResponseDto> updateUserById(UUID id, UserRequestDto userRequestDto) {
        log.info("Update user by id {}", id);
        var response = userService.updateUserById(id, userRequestDto);
        log.info("Update user successful: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<UserResponseDto>> searchUsers(Integer offset, Integer limit, List<String> sort, List<String> search) {
        log.info("Search users based on criteria");
        var response = userService.searchUser(offset, limit, sort, search);
        log.info("Search for users successful");
        return ResponseEntity.ok(response);
    }

}
