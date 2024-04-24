package com.tech.springboot.controller;

import com.fpt.training.aio.lending.api.RoleApi;
import com.fpt.training.aio.lending.model.PermissionResponseDto;
import com.fpt.training.aio.lending.model.RolePermissionRequestDto;
import com.fpt.training.aio.lending.model.RoleRequestDto;
import com.fpt.training.aio.lending.model.RoleResponseDto;
import com.tech.springboot.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class RoleController implements RoleApi {
    private final RoleService roleService;

    @Override
    public ResponseEntity<RoleResponseDto> addRole(RoleRequestDto roleRequestDto) {
        RoleResponseDto response = roleService.addRole(roleRequestDto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<PermissionResponseDto>> assignPermission(RolePermissionRequestDto rolePermissionRequestDto) {
        List<PermissionResponseDto> response = roleService.assignPermissionToRole(rolePermissionRequestDto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteRoleByID(UUID id) {
        roleService.deleteRoleById(id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<RoleResponseDto> getRoleById(UUID id) {
        RoleResponseDto response = roleService.getRoleById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<RoleResponseDto>> getRoles() {
        List<RoleResponseDto> roles = roleService.getRoles();
        return ResponseEntity.ok(roles);
    }

    @Override
    public ResponseEntity<RoleResponseDto> updateRoleByID(UUID id, RoleRequestDto roleRequestDto) {
        RoleResponseDto response = roleService.updateRoleById(id, roleRequestDto);
        return ResponseEntity.ok(response);
    }
}
