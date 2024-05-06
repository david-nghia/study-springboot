package com.tech.springboot.controller;

import com.tech.springboot.lending.api.RoleApi;
import com.tech.springboot.lending.model.PermissionResponseDto;
import com.tech.springboot.lending.model.RolePermissionRequestDto;
import com.tech.springboot.lending.model.RoleRequestDto;
import com.tech.springboot.lending.model.RoleResponseDto;
import com.tech.springboot.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RoleController implements RoleApi {
    private final RoleService roleService;

    @Override
    public ResponseEntity<RoleResponseDto> addRole(RoleRequestDto roleRequestDto) {
        log.info("Role name {}", roleRequestDto.getName());
        var response = roleService.addRole(roleRequestDto);
        log.info("Add role successful: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<PermissionResponseDto>> assignPermission(RolePermissionRequestDto rolePermissionRequestDto) {
        log.info("Assign permissions to role by id: {}", rolePermissionRequestDto.getRoleId());
        List<PermissionResponseDto> response = roleService.assignPermissionToRole(rolePermissionRequestDto);
        log.info("Assign permissions to role successful");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteRoleByID(UUID id) {
        log.info("Delete role by id: {}", id);
        roleService.deleteRoleById(id);
        log.info("Delete role successful");
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<RoleResponseDto> getRoleById(UUID id) {
        log.info("Get role by id: {}", id);
        var response = roleService.getRoleById(id);
        log.info("Get role successful: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<RoleResponseDto>> getRoles() {
        log.info("Get active roles");
        List<RoleResponseDto> roles = roleService.getRoles();
        log.info("Get list of role successful");
        return ResponseEntity.ok(roles);
    }

    @Override
    public ResponseEntity<RoleResponseDto> updateRoleByID(UUID id, RoleRequestDto roleRequestDto) {
        log.info("Update role by id: {}", id);
        RoleResponseDto response = roleService.updateRoleById(id, roleRequestDto);
        log.info("Update role successful: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<RoleResponseDto>> searchRoles(Integer offset, Integer limit, List<String> sort, List<String> search) {
        log.info("Search roles based on criteria");
        var response = roleService.searchRole(offset, limit, sort, search);
        log.info("Search for roles successful");
        return ResponseEntity.ok(response);
    }
}
