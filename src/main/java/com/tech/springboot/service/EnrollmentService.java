package com.tech.springboot.service;

import com.tech.springboot.entity.Enrollment;
import com.tech.springboot.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public void saveEnrollment(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
    }

    public void updateEnrollment(String id, Enrollment enrollment) {
        Enrollment e = enrollmentRepository.findByEnrollmentId(id);
        e.setUser(enrollment.getUser());
        e.setCourse(enrollment.getCourse());
        e.setStatus(enrollment.getStatus());
        enrollmentRepository.save(e);
    }
}
