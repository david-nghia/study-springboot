package com.tech.springboot.mapper;

import com.fpt.training.aio.lending.model.RoleResponseDto;
import com.tech.springboot.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    RoleResponseDto toDto(Role role);
    List<RoleResponseDto> toDtoList(Set<Role> roles);
    List<RoleResponseDto> toDtoList(List<Role> roles);
}
