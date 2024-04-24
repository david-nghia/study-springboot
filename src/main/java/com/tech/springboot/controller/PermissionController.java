package com.tech.springboot.controller;

import com.fpt.training.aio.lending.api.PermissionApi;
import com.fpt.training.aio.lending.model.PermissionRequestDto;
import com.fpt.training.aio.lending.model.PermissionResponseDto;
import com.tech.springboot.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class PermissionController implements PermissionApi {
    private final PermissionService permissionService;
    @Override
    public ResponseEntity<PermissionResponseDto> addPermission(PermissionRequestDto permissionRequestDto) {
        PermissionResponseDto response = permissionService.addPermission(permissionRequestDto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletePermissionById(UUID id) {
        permissionService.deletePermissionById(id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<PermissionResponseDto> getPermissionById(UUID id) {
        PermissionResponseDto response = permissionService.getPermissionById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<PermissionResponseDto>> getPermissions() {
        List<PermissionResponseDto> permissions = permissionService.getPermissions();
        return ResponseEntity.ok(permissions);
    }

//    @Override
//    public ResponseEntity<PermissionResponseDto> updatePermissionById(UUID id, PermissionRequestDto permissionRequestDto) {
//        PermissionResponseDto response = permissionService.updatePermissionById(id, permissionRequestDto);
//        return ResponseEntity.ok(response);
//    }
}
