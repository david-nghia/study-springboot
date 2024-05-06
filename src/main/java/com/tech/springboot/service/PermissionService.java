package com.tech.springboot.service;

import com.tech.springboot.lending.model.PermissionRequestDto;
import com.tech.springboot.lending.model.PermissionResponseDto;

import java.util.List;
import java.util.UUID;

public interface PermissionService {
    PermissionResponseDto addPermission(PermissionRequestDto permissionRequestDto);

    List<PermissionResponseDto> getPermissions();

    PermissionResponseDto updatePermissionById(UUID uuid, PermissionRequestDto permissionRequestDto);

    PermissionResponseDto getPermissionById(UUID id);

    void deletePermissionById(UUID uuid);
    List<PermissionResponseDto> searchPermission(Integer offset, Integer limit, List<String> sortBy, List<String> search);

}
