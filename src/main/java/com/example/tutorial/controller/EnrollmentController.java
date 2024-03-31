package com.example.tutorial.controller;

import com.example.tutorial.model.Enrollment;
import com.example.tutorial.service.EnrollmentService;
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
    public void updateEnrollment(@PathVariable(value = "id") Integer id
            , @RequestBody Enrollment enrollment) throws ChangeSetPersister.NotFoundException {
        enrollmentService.updateEnrollment(id, enrollment);
    }
}
