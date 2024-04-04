package com.tech.springboot.service;

import com.tech.springboot.dto.EnrollmentCreateDTO;
import com.tech.springboot.dto.EnrollmentUpdateDTO;
import com.tech.springboot.entity.Enrollment;
import com.tech.springboot.mapper.EnrollmentMapper;
import com.tech.springboot.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public void saveEnrollment(EnrollmentCreateDTO enrollmentCreateDTO) {
        Enrollment enrollment = EnrollmentMapper.INSTANCE.toEntity(enrollmentCreateDTO);
        enrollmentRepository.save(enrollment);
    }

    public void updateEnrollment(UUID id, EnrollmentUpdateDTO enrollmentUpdateDTO) {
        Enrollment enrollment = EnrollmentMapper.INSTANCE.toEntity(enrollmentUpdateDTO);
        Optional<Enrollment> optional = enrollmentRepository.findById(id);
        Enrollment e = optional.orElseThrow(() -> new RuntimeException("Enrollment not found"));
        e.setStatus(enrollment.getStatus());
        enrollmentRepository.save(e);
    }
}
