package com.tech.springboot.mapper;

import com.tech.springboot.dto.EnrollmentCreateDTO;
import com.tech.springboot.dto.EnrollmentUpdateDTO;
import com.tech.springboot.entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnrollmentMapper {
    EnrollmentMapper INSTANCE = Mappers.getMapper(EnrollmentMapper.class);

    EnrollmentCreateDTO toDTO(Enrollment enrollment);

    Enrollment toEntity(EnrollmentCreateDTO enrollmentCreateDTO);

    Enrollment toEntity(EnrollmentUpdateDTO enrollmentUpdateDTO);

}
