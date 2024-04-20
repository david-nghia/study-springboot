package com.tech.springboot.service.impl;

import com.fpt.training.aio.lending.model.EnrollmentRequestDto;
import com.tech.springboot.entity.Enrollment;
import com.tech.springboot.mapper.EnrollmentMapper;
import com.tech.springboot.repository.EnrollmentRepository;
import com.tech.springboot.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public EnrollmentRequestDto saveEnrollment(EnrollmentRequestDto enrollmentRequestDto) {
        Enrollment eToEntity = EnrollmentMapper.INSTANCE.toEntity(enrollmentRequestDto);
        Enrollment saveEnrollment = enrollmentRepository.save(eToEntity);
        return EnrollmentMapper.INSTANCE.toDTO(saveEnrollment);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public EnrollmentRequestDto updateEnrollment(UUID uuid, EnrollmentRequestDto enrollmentRequestDto) {
        Optional<Enrollment> eOptional = enrollmentRepository.findById(uuid);
        Enrollment eToUpdate = eOptional.get();
        eToUpdate.setStatus(enrollmentRequestDto.getStatus());
        Enrollment saveEnrollment = enrollmentRepository.save(eToUpdate);
        return EnrollmentMapper.INSTANCE.toDTO(saveEnrollment);
    }
}
