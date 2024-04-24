package com.tech.springboot.mapper;

import com.fpt.training.aio.lending.model.RegisterUserDto;
import com.fpt.training.aio.lending.model.UserRequestDto;
import com.fpt.training.aio.lending.model.UserResponseDto;
import com.tech.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "modifiedDate", target = "modifiedDate", qualifiedByName = "offsetDatetimeToString")
    @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "offsetDatetimeToString")
    @Mapping(source = "dob", target = "dob", qualifiedByName = "offsetDatetimeToString")
    UserResponseDto toDTO(User user);

    @Mapping(source = "dob", target = "dob", qualifiedByName = "offsetDatetimeToString")
    RegisterUserDto toDto(User user);

    @Mapping(source = "dob", target = "dob", qualifiedByName = "stringToOffsetDatetime")
    User toEntity(UserRequestDto userRequestDto);

    @Mapping(source = "dob", target = "dob", qualifiedByName = "stringToOffsetDatetime")
    User toEntity(RegisterUserDto registerUserDto);

    List<UserResponseDto> toDTOs(List<User> users);

    @Named("offsetDatetimeToString")
    default String offsetDatetimeToString(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toString();
    }

    @Named("stringToOffsetDatetime")
    default OffsetDateTime stringToOffsetDatetime(String str) {
        return OffsetDateTime.parse(str);
    }
}
