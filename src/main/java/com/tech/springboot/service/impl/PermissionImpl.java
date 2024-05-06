package com.tech.springboot.service.impl;

import com.tech.springboot.constant.GlobalFunction;
import com.tech.springboot.lending.model.PermissionRequestDto;
import com.tech.springboot.lending.model.PermissionResponseDto;
import com.tech.springboot.mapper.UserMapper;
import com.tech.springboot.model.entity.Permission;
import com.tech.springboot.enums.ExceptionAlertEnum;
import com.tech.springboot.enums.StatusEnum;
import com.tech.springboot.exception.BusinessException;
import com.tech.springboot.exception.dto.AlertMessage;
import com.tech.springboot.mapper.PermissionMapper;
import com.tech.springboot.model.entity.User;
import com.tech.springboot.repository.PermissionRepository;
import com.tech.springboot.service.PermissionService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PermissionImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final EntityManager entityManager;

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

    @Override
    public List<PermissionResponseDto> searchPermission(Integer offset, Integer limit, List<String> sort, List<String> search) {
        List<?> rawPermissions = GlobalFunction.filter(entityManager, Permission.class, offset, limit, sort, search);
        List<Permission> permissions = null;

        if (rawPermissions != null && rawPermissions.size() > 0 && rawPermissions.get(0) instanceof Permission) {
            permissions = (List<Permission>) rawPermissions;
        } else {
            permissions = Collections.emptyList();
        }
        return PermissionMapper.INSTANCE.toDtoList(permissions);
    }

    private String generateKey(String name) {
        return name.toUpperCase().replaceAll("\\s+", " ").replace(" ", "_");
    }
}
