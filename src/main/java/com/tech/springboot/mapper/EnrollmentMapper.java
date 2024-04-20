package com.tech.springboot.mapper;

import com.fpt.training.aio.lending.model.EnrollmentRequestDto;
import com.tech.springboot.entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnrollmentMapper {
    EnrollmentMapper INSTANCE = Mappers.getMapper(EnrollmentMapper.class);

    EnrollmentRequestDto toDTO(Enrollment enrollment);

    Enrollment toEntity(EnrollmentRequestDto enrollmentRequestDto);

}
