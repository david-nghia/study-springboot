package com.tech.springboot.service;

import com.fpt.training.aio.lending.model.EnrollmentRequestDto;

import java.util.UUID;

public interface EnrollmentService {
    EnrollmentRequestDto saveEnrollment(EnrollmentRequestDto enrollmentRequestDto);

    EnrollmentRequestDto updateEnrollment(UUID uuid, EnrollmentRequestDto enrollmentRequestDto);

//    private final EnrollmentRepository enrollmentRepository;
//
//    public void saveEnrollment(EnrollmentCreateDTO enrollmentCreateDTO) {
//        Enrollment enrollment = EnrollmentMapper.INSTANCE.toEntity(enrollmentCreateDTO);
//        enrollmentRepository.save(enrollment);
//    }
//
//    public void updateEnrollment(UUID id, EnrollmentUpdateDTO enrollmentUpdateDTO) {
//        Enrollment enrollment = EnrollmentMapper.INSTANCE.toEntity(enrollmentUpdateDTO);
//        Optional<Enrollment> optional = enrollmentRepository.findById(id);
//        Enrollment e = optional.orElseThrow(() -> new RuntimeException("Enrollment not found"));
//        e.setStatus(enrollment.getStatus());
//        enrollmentRepository.save(e);
//    }
}
