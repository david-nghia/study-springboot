package com.tech.springboot.mapper;

import com.fpt.training.aio.lending.model.EnrollmentRequestDto;
import com.fpt.training.aio.lending.model.EnrollmentResponseDto;
import com.tech.springboot.entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper
public interface EnrollmentMapper {
    EnrollmentMapper INSTANCE = Mappers.getMapper(EnrollmentMapper.class);

    @Mapping(source = "modifiedDate", target = "modifiedDate", qualifiedByName = "offsetDatetimeToString")
    @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "offsetDatetimeToString")
    EnrollmentResponseDto toDto(Enrollment enrollment);
    List<EnrollmentResponseDto> toDtoList(List<Enrollment> enrollments);

    Enrollment toEntity(EnrollmentRequestDto enrollmentRequestDto);

    @Named("offsetDatetimeToString")
    default String offsetDatetimeToString(OffsetDateTime offsetDateTime){
        return offsetDateTime.toString();
    }
}
