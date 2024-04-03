package com.tech.springboot.controller;

import com.tech.springboot.entity.Enrollment;
import com.tech.springboot.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping()
    public void createEnrollment(@RequestBody Enrollment enrollment) {
        enrollmentService.saveEnrollment(enrollment);
    }

    @PutMapping("/{id}")
    public void updateEnrollment(@PathVariable(value = "id") String id
            , @RequestBody Enrollment enrollment) throws ParseException {
        enrollmentService.updateEnrollment(id, enrollment);
    }
}
