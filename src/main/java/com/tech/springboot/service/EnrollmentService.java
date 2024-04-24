package com.tech.springboot.service;



import com.fpt.training.aio.lending.model.EnrollmentRequestDto;
import com.fpt.training.aio.lending.model.EnrollmentResponseDto;

import java.util.List;
import java.util.UUID;

public interface EnrollmentService {
    EnrollmentResponseDto addEnrollment(EnrollmentRequestDto enrollmentRequestDto);

    List<EnrollmentResponseDto> getEnrollments();

    EnrollmentResponseDto updateEnrollmentById(UUID uuid, EnrollmentRequestDto enrollmentRequestDto);

    EnrollmentResponseDto getEnrollmentById(UUID uuid);

    void deleteEnrollmentById(UUID uuid);
}
