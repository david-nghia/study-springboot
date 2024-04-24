package com.tech.springboot.service;

import com.fpt.training.aio.lending.model.PermissionRequestDto;
import com.fpt.training.aio.lending.model.PermissionResponseDto;

import java.util.List;
import java.util.UUID;

public interface PermissionService {
    PermissionResponseDto addPermission(PermissionRequestDto permissionRequestDto);

    List<PermissionResponseDto> getPermissions();

    PermissionResponseDto updatePermissionById(UUID uuid, PermissionRequestDto permissionRequestDto);

    PermissionResponseDto getPermissionById(UUID id);

    void deletePermissionById(UUID uuid);

}
