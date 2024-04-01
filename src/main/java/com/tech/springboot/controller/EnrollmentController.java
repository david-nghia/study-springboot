package com.tech.springboot.controller;

import com.tech.springboot.model.Enrollment;
import com.tech.springboot.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping()
    public void createEnrollment(@RequestBody Enrollment enrollment) {
        enrollmentService.saveEnrollment(enrollment);
    }

    @PutMapping("/{id}")
    public void updateEnrollment(@PathVariable(value = "id") String id
            , @RequestBody Enrollment enrollment) throws ChangeSetPersister.NotFoundException {
        enrollmentService.updateEnrollment(id, enrollment);
    }
}
