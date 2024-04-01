package com.tech.springboot.service;

import com.tech.springboot.model.Enrollment;
import com.tech.springboot.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

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
