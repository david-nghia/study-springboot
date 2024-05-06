package com.tech.springboot.service.impl;

import com.tech.springboot.constant.GlobalFunction;
import com.tech.springboot.lending.model.*;
import com.tech.springboot.model.entity.Role;
import com.tech.springboot.model.entity.User;
import com.tech.springboot.enums.ExceptionAlertEnum;
import com.tech.springboot.enums.StatusEnum;
import com.tech.springboot.exception.BusinessException;
import com.tech.springboot.exception.dto.AlertMessage;
import com.tech.springboot.mapper.RoleMapper;
import com.tech.springboot.mapper.UserMapper;
import com.tech.springboot.repository.RoleRepository;
import com.tech.springboot.repository.UserRepository;
import com.tech.springboot.service.UserService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final EntityManager entityManager;

    @Override
    public RegisterUserDto register(RegisterUserDto registerUserDto) {
        Optional<User> userOpt = userRepository.findUserByUsername(registerUserDto.getUsername());
        if (!userOpt.isEmpty()) {
            throw new BusinessException("Register with exits user",
                    AlertMessage.alert(ExceptionAlertEnum.USER_EXIST_DB));
        }
        registerUserDto.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        User userRegister = UserMapper.INSTANCE.toEntity(registerUserDto);
        userRegister.setStatus(StatusEnum.ACTIVE);
        userRegister.setCreatedBy("admin");
        userRegister.setCreatedDate(OffsetDateTime.now());
        userRegister.setModifiedBy("admin");
        userRegister.setModifiedDate(OffsetDateTime.now());

        User userDb = userRepository.save(userRegister);
        return UserMapper.INSTANCE.toDto(userDb);
    }

    @Override
    public List<UserResponseDto> getUsers() {
        List<User> activeUsers = userRepository.findUsersByStatus(StatusEnum.ACTIVE);
        if (activeUsers.isEmpty()) {
            return Collections.emptyList();
        }
        List<UserResponseDto> users = UserMapper.INSTANCE.toDTOs(activeUsers);
        return users;
    }

    @Override
    public UserResponseDto updateUserById(UUID uuid, UserRequestDto userRequestDto) {
        Optional<User> userOpt = userRepository.findUserById(uuid);
        if (userOpt.isEmpty()) {
            throw new BusinessException("User does not exist",
                    AlertMessage.alert(ExceptionAlertEnum.USER_NOT_FOUND));
        }
        User userToUpdate = userOpt.get();
        if (userToUpdate.getStatus() != StatusEnum.ACTIVE) {
            throw new BusinessException("User is non-active",
                    AlertMessage.alert(ExceptionAlertEnum.USER_NON_ACTIVE));
        }
        userToUpdate.setUsername(userRequestDto.getUsername());
        userToUpdate.setEmail(userRequestDto.getEmail());
        userToUpdate.setDob(OffsetDateTime.parse(userRequestDto.getDob()));
        return UserMapper.INSTANCE.toDTO(userToUpdate);
    }

    @Override
    public UserResponseDto getUserById(UUID uuid) {
        Optional<User> userOpt = userRepository.findUserById(uuid);
        if (userOpt.isEmpty()) {
            throw new BusinessException("User does not exist",
                    AlertMessage.alert(ExceptionAlertEnum.USER_NOT_FOUND));
        }
        User user = userOpt.get();
        if (user.getStatus() != StatusEnum.ACTIVE) {
            throw new BusinessException("User is non-active",
                    AlertMessage.alert(ExceptionAlertEnum.USER_NON_ACTIVE));
        }
        return UserMapper.INSTANCE.toDTO(user);
    }

    @Override
    public void deleteUserById(UUID uuid) {
        Optional<User> userOpt = userRepository.findUserById(uuid);

        if (userOpt.isEmpty()) {
            throw new BusinessException("User does not exist",
                    AlertMessage.alert(ExceptionAlertEnum.USER_NOT_FOUND));
        }
        User userToDelete = userOpt.get();
        if (userToDelete.getStatus() != StatusEnum.ACTIVE) {
            throw new BusinessException("User is non-active",
                    AlertMessage.alert(ExceptionAlertEnum.USER_NON_ACTIVE));
        }
        userToDelete.setStatus(StatusEnum.DELETED);
        userRepository.save(userToDelete);
    }

    @Override
    public List<RoleResponseDto> assignRoleToUser(UserRoleRequestDto userRoleRequestDto) {
        Optional<User> userOpt = userRepository.findUserById(userRoleRequestDto.getUserId());
        if (userOpt.isEmpty()) {
            throw new BusinessException("User does not exist",
                    AlertMessage.alert(ExceptionAlertEnum.USER_NOT_FOUND));
        }
        User user = userOpt.get();
        List<Role> allRoles = roleRepository.findAllById(userRoleRequestDto.getRoleIds());
        Set<Role> roles = new HashSet<>(allRoles);
        user.setRoles(roles);
        userRepository.save(user);
        List<RoleResponseDto> roleResp = RoleMapper.INSTANCE.toDtoList(roles);
        return roleResp;
    }

    @Override
    public List<UserResponseDto> searchUser(Integer offset, Integer limit, List<String> sort, List<String> search) {
        List<?> rawUsers = GlobalFunction.filter(entityManager, User.class, offset, limit, sort, search);
        List<User> users = null;

        if (rawUsers != null && rawUsers.size() > 0 && rawUsers.get(0) instanceof User) {
            users = (List<User>) rawUsers;
        } else {
            users = Collections.emptyList();
        }
        return UserMapper.INSTANCE.toDTOs(users);
    }
}
