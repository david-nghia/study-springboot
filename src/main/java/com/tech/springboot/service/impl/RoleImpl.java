package com.tech.springboot.service.impl;

import com.tech.springboot.constant.GlobalFunction;
import com.tech.springboot.lending.model.*;
import com.tech.springboot.model.entity.Permission;
import com.tech.springboot.model.entity.Role;
import com.tech.springboot.enums.ExceptionAlertEnum;
import com.tech.springboot.enums.StatusEnum;
import com.tech.springboot.exception.BusinessException;
import com.tech.springboot.exception.dto.AlertMessage;
import com.tech.springboot.mapper.PermissionMapper;
import com.tech.springboot.mapper.RoleMapper;
import com.tech.springboot.model.entity.User;
import com.tech.springboot.repository.PermissionRepository;
import com.tech.springboot.repository.RoleRepository;
import com.tech.springboot.service.RoleService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoleImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    private final EntityManager entityManager;

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

    @Override
    public List<RoleResponseDto> searchRole(Integer offset, Integer limit, List<String> sortBy, List<String> search) {
        List<?> rawRoles = GlobalFunction.filter(entityManager, Role.class, offset, limit, sortBy, search);
        List<Role> roles = null;

        if (rawRoles != null && rawRoles.size() > 0 && rawRoles.get(0) instanceof Role) {
            roles = (List<Role>) rawRoles;
        } else {
            roles = Collections.emptyList();
        }
        return RoleMapper.INSTANCE.toDtoList(roles);
    }

    private String generateKey(String name) {
        return name.toUpperCase().replace("\\s+", "").replace(" ", "_");
    }
}
