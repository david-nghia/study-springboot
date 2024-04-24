package com.tech.springboot.service.impl;

import com.fpt.training.aio.lending.model.PermissionResponseDto;
import com.fpt.training.aio.lending.model.RolePermissionRequestDto;
import com.fpt.training.aio.lending.model.RoleRequestDto;
import com.fpt.training.aio.lending.model.RoleResponseDto;
import com.tech.springboot.entity.Permission;
import com.tech.springboot.entity.Role;
import com.tech.springboot.enums.ExceptionAlertEnum;
import com.tech.springboot.enums.StatusEnum;
import com.tech.springboot.exception.BusinessException;
import com.tech.springboot.exception.dto.AlertMessage;
import com.tech.springboot.mapper.PermissionMapper;
import com.tech.springboot.mapper.RoleMapper;
import com.tech.springboot.repository.PermissionRepository;
import com.tech.springboot.repository.RoleRepository;
import com.tech.springboot.repository.UserRepository;
import com.tech.springboot.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoleImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public RoleResponseDto addRole(RoleRequestDto roleRequestDto) {
        Optional<Role> roleOpt = roleRepository.findRoleByName(roleRequestDto.getName());
        if (!roleOpt.isEmpty()) {
            throw new BusinessException("Add role exist",
                    AlertMessage.alert(ExceptionAlertEnum.ROLE_EXIST_DB));
        }
        Role role = roleOpt.get();
        role.setRoleKey(generateKey(role.getName()));
        Role roleDb = roleRepository.save(role);
        return RoleMapper.INSTANCE.toDto(roleDb);
    }

    @Override
    public List<RoleResponseDto> getRoles() {
        List<Role> rolesRep = roleRepository.findAll();
        if (rolesRep.isEmpty()) {
            return Collections.emptyList();
        }
        List<RoleResponseDto> roles = RoleMapper.INSTANCE.toDtoList(rolesRep);
        return roles;
    }

    @Override
    public RoleResponseDto updateRoleById(UUID uuid, RoleRequestDto roleRequestDto) {
        Optional<Role> roleOpt = roleRepository.findRoleById(uuid);
        if (roleOpt.isEmpty()) {
            throw new BusinessException("Role does not exist",
                    AlertMessage.alert(ExceptionAlertEnum.ROLE_NOT_FOUND));
        }
        Role roleToUpdate = roleOpt.get();
        if (roleToUpdate.getStatus() != StatusEnum.ACTIVE) {
            throw new BusinessException("Role is non-active",
                    AlertMessage.alert(ExceptionAlertEnum.ROLE_NON_ACTIVE));
        }
        String newName = roleOpt.get().getName();
        roleToUpdate.setName(newName);
        roleToUpdate.setRoleKey(generateKey(newName));
        return RoleMapper.INSTANCE.toDto(roleToUpdate);
    }

    @Override
    public RoleResponseDto getRoleById(UUID id) {
        Optional<Role> roleOpt = roleRepository.findRoleById(id);

        if (roleOpt.isEmpty()) {
            throw new BusinessException("Role does not exist",
                    AlertMessage.alert(ExceptionAlertEnum.ROLE_NOT_FOUND));
        }
        Role role = roleOpt.get();
        return RoleMapper.INSTANCE.toDto(role);
    }

    @Override
    public void deleteRoleById(UUID uuid) {
        Optional<Role> roleOpt = roleRepository.findRoleById(uuid);

        if (roleOpt.isEmpty()) {
            throw new BusinessException("Role does not exist",
                    AlertMessage.alert(ExceptionAlertEnum.ROLE_NOT_FOUND));
        }
        Role roleToDelete = roleOpt.get();
        if (roleToDelete.getStatus() != StatusEnum.ACTIVE) {
            throw new BusinessException("Role is non-active",
                    AlertMessage.alert(ExceptionAlertEnum.ROLE_NON_ACTIVE));
        }
        roleToDelete.setStatus(StatusEnum.DELETED);
        roleRepository.save(roleToDelete);
    }

    @Override
    public List<PermissionResponseDto> assignPermissionToRole(RolePermissionRequestDto rolePermissionRequestDto) {
        Optional<Role> roleOpt = roleRepository.findRoleById(rolePermissionRequestDto.getRoleId());
        if (roleOpt.isEmpty()) {
            throw new BusinessException("Role does not exist",
                    AlertMessage.alert(ExceptionAlertEnum.ROLE_NOT_FOUND));
        }
        Role role = roleOpt.get();
        List<Permission> allPermissions = permissionRepository.findAllById(rolePermissionRequestDto.getPermissionIds());
        Set<Permission> permissions = new HashSet<>(allPermissions);
        role.setPermissions(permissions);
        roleRepository.save(role);
        List<PermissionResponseDto> resp = PermissionMapper.INSTANCE.toDtoList(permissions);
        return resp;
    }

    private String generateKey(String name) {
        return name.toUpperCase().replace("\\s+", "").replace(" ", "_");
    }
}
