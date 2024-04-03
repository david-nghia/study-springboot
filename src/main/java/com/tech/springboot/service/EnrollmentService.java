package com.tech.springboot.service;

import com.tech.springboot.entity.Enrollment;
import com.tech.springboot.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public void saveEnrollment(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
    }

    public void updateEnrollment(String id, Enrollment enrollment) throws ParseException {
        Enrollment e = new Enrollment();
        e.setId(enrollment.getId());
        e.setUser(enrollment.getUser());
        e.setCourse(enrollment.getCourse());
        e.setStatus(enrollment.getStatus());
        e.setEnrollmentDate(new SimpleDateFormat("yyyy-MM-dd").parse("2024-05-10"));
        e.setCompletionDate(null);
        e.setNote(enrollment.getNote());
        enrollmentRepository.save(e);
    }
}
