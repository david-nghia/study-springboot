package com.tech.springboot.mapper;

import com.fpt.training.aio.lending.model.UserResponseDto;
import com.tech.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDto toDTO(User user);

    List<UserResponseDto> toDTOs(List<User> users);
}
