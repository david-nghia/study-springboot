package com.tech.springboot.service;

import com.tech.springboot.lending.model.*;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    RoleResponseDto addRole(RoleRequestDto roleRequestDto);

    List<RoleResponseDto> getRoles();

    RoleResponseDto updateRoleById(UUID uuid, RoleRequestDto roleRequestDto);

    RoleResponseDto getRoleById(UUID id);

    void deleteRoleById(UUID uuid);

    List<PermissionResponseDto> assignPermissionToRole(RolePermissionRequestDto rolePermissionRequestDto);

    List<RoleResponseDto> searchRole(Integer offset, Integer limit, List<String> sortBy, List<String> search);
}
