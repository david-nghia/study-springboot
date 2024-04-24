package com.tech.springboot.service;

import com.fpt.training.aio.lending.model.PermissionResponseDto;
import com.fpt.training.aio.lending.model.RolePermissionRequestDto;
import com.fpt.training.aio.lending.model.RoleRequestDto;
import com.fpt.training.aio.lending.model.RoleResponseDto;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    RoleResponseDto addRole(RoleRequestDto roleRequestDto);

    List<RoleResponseDto> getRoles();

    RoleResponseDto updateRoleById(UUID uuid, RoleRequestDto roleRequestDto);

    RoleResponseDto getRoleById(UUID id);

    void deleteRoleById(UUID uuid);

    List<PermissionResponseDto> assignPermissionToRole(RolePermissionRequestDto rolePermissionRequestDto);
}
