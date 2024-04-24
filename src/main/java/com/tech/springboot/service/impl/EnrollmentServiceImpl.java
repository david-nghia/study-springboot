package com.tech.springboot.service.impl;

import com.fpt.training.aio.lending.model.EnrollmentRequestDto;
import com.fpt.training.aio.lending.model.EnrollmentResponseDto;
import com.tech.springboot.repository.EnrollmentRepository;
import com.tech.springboot.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;


    @Override
    public EnrollmentResponseDto addEnrollment(EnrollmentRequestDto enrollmentRequestDto) {
        return null;
    }

    @Override
    public List<EnrollmentResponseDto> getEnrollments() {
        return null;
    }

    @Override
    public EnrollmentResponseDto updateEnrollmentById(UUID uuid, EnrollmentRequestDto enrollmentRequestDto) {
        return null;
    }

    @Override
    public EnrollmentResponseDto getEnrollmentById(UUID uuid) {
        return null;
    }

    @Override
    public void deleteEnrollmentById(UUID uuid) {

    }
}
