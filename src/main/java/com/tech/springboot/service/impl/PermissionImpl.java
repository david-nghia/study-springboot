package com.tech.springboot.service.impl;

import com.fpt.training.aio.lending.model.PermissionRequestDto;
import com.fpt.training.aio.lending.model.PermissionResponseDto;
import com.tech.springboot.entity.Permission;
import com.tech.springboot.entity.Role;
import com.tech.springboot.enums.ExceptionAlertEnum;
import com.tech.springboot.enums.StatusEnum;
import com.tech.springboot.exception.BusinessException;
import com.tech.springboot.exception.dto.AlertMessage;
import com.tech.springboot.mapper.PermissionMapper;
import com.tech.springboot.mapper.RoleMapper;
import com.tech.springboot.repository.PermissionRepository;
import com.tech.springboot.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PermissionImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Override
    public PermissionResponseDto addPermission(PermissionRequestDto permissionRequestDto) {
        Optional<Permission> perOpt = permissionRepository.findPermissionByName(permissionRequestDto.getName());
        if (!perOpt.isEmpty()) {
            throw new BusinessException("Add permission exist",
                    AlertMessage.alert(ExceptionAlertEnum.PERMISSION_EXIST_DB));
        }
        Permission permission = perOpt.get();
        String permission_key = generateKey(permissionRequestDto.getName());
        permission.setPermissionKey(permission_key);
        Permission permissionDB = permissionRepository.save(permission);
        return PermissionMapper.INSTANCE.toDto(permissionDB);
    }

    @Override
    public List<PermissionResponseDto> getPermissions() {
        List<Permission> permissionsRep = permissionRepository.findAll();
        if (permissionsRep.isEmpty()) {
            return Collections.emptyList();
        }
        List<PermissionResponseDto> permissions = PermissionMapper.INSTANCE.toDtoList(permissionsRep);
        return permissions;
    }

    @Override
    public PermissionResponseDto updatePermissionById(UUID uuid, PermissionRequestDto permissionRequestDto) {
        Optional<Permission> perOpt = permissionRepository.findPermissionByName(permissionRequestDto.getName());
        if (!perOpt.isEmpty()) {
            throw new BusinessException("Add permission exist",
                    AlertMessage.alert(ExceptionAlertEnum.PERMISSION_EXIST_DB));
        }
        Permission perToUpdate = perOpt.get();
        if (perToUpdate.getStatus() != StatusEnum.ACTIVE) {
            throw new BusinessException("Permission is non-active",
                    AlertMessage.alert(ExceptionAlertEnum.PERMISSION_NON_ACTIVE));
        }
        String newName = permissionRequestDto.getName();
        perToUpdate.setName(newName);
        perToUpdate.setPermissionKey(generateKey(newName));
        Permission permissionDb = permissionRepository.save(perToUpdate);
        return PermissionMapper.INSTANCE.toDto(permissionDb);
    }

    @Override
    public PermissionResponseDto getPermissionById(UUID id) {
        Optional<Permission> perOpt = permissionRepository.findPermissionById(id);
        if (!perOpt.isEmpty()) {
            throw new BusinessException("Add permission exist",
                    AlertMessage.alert(ExceptionAlertEnum.PERMISSION_EXIST_DB));
        }
        Permission permission = perOpt.get();
        if (permission.getStatus() != StatusEnum.ACTIVE) {
            throw new BusinessException("Permission is non-active",
                    AlertMessage.alert(ExceptionAlertEnum.PERMISSION_NON_ACTIVE));
        }
        return PermissionMapper.INSTANCE.toDto(permission);
    }

    @Override
    public void deletePermissionById(UUID uuid) {
        Optional<Permission> perOpt = permissionRepository.findPermissionById(uuid);
        if (!perOpt.isEmpty()) {
            throw new BusinessException("Add permission exist",
                    AlertMessage.alert(ExceptionAlertEnum.PERMISSION_EXIST_DB));
        }
        Permission perToDelete = perOpt.get();
        if (perToDelete.getStatus() != StatusEnum.ACTIVE) {
            throw new BusinessException("Permission is non-active",
                    AlertMessage.alert(ExceptionAlertEnum.PERMISSION_NON_ACTIVE));
        }
        perToDelete.setStatus(StatusEnum.DELETED);
        permissionRepository.save(perToDelete);
    }

    private String generateKey(String name) {
        return name.toUpperCase().replaceAll("\\s+", " ").replace(" ", "_");
    }
}
