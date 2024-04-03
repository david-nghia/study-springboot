package com.tech.springboot.mapper;

import com.tech.springboot.dto.UserResponseDTO;
import com.tech.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDTO toDTO(User user);

    List<UserResponseDTO> toDTOs(List<User> users);
}
