package com.tech.springboot.controller;

import com.tech.springboot.lending.api.PermissionApi;
import com.tech.springboot.lending.model.PermissionRequestDto;
import com.tech.springboot.lending.model.PermissionResponseDto;
import com.tech.springboot.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PermissionController implements PermissionApi {
    private final PermissionService permissionService;

    @Override
    public ResponseEntity<PermissionResponseDto> addPermission(PermissionRequestDto permissionRequestDto) {
        log.info("Permission name {}", permissionRequestDto.getName());
        var response = permissionService.addPermission(permissionRequestDto);
        log.info("Add permission successful: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletePermissionById(UUID id) {
        log.info("Delete permission by id: {}", id);
        permissionService.deletePermissionById(id);
        log.info("Delete permission successful");
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<PermissionResponseDto> getPermissionById(UUID id) {
        log.info("Get permission by id: {}", id);
        PermissionResponseDto response = permissionService.getPermissionById(id);
        log.info("Get permission successful: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<PermissionResponseDto>> getPermissions() {
        log.info("Get active permissions");
        List<PermissionResponseDto> permissions = permissionService.getPermissions();
        log.info("Get list of permission successful");
        return ResponseEntity.ok(permissions);
    }

    @Override
    public ResponseEntity<PermissionResponseDto> updatePermissionById(UUID id, PermissionRequestDto permissionRequestDto) {
        log.info("Update permission by id: {}", id);
        PermissionResponseDto response = permissionService.updatePermissionById(id, permissionRequestDto);
        log.info("Update permission successful: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<PermissionResponseDto>> searchPermissions(Integer offset, Integer limit, List<String> sort, List<String> search) {
        log.info("Search permissions based on criteria");
        var response = permissionService.searchPermission(offset, limit, sort, search);
        log.info("Search for permissions successful");
        return ResponseEntity.ok(response);
    }
}
