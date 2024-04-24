package com.tech.springboot.mapper;

import com.fpt.training.aio.lending.model.PermissionResponseDto;
import com.tech.springboot.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface PermissionMapper {
    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    PermissionResponseDto toDto(Permission permission);
    List<PermissionResponseDto> toDtoList(Set<Permission> permissions);
    List<PermissionResponseDto> toDtoList(List<Permission> permissions);
}
