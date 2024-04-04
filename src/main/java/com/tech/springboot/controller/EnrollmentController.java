package com.tech.springboot.controller;

import com.tech.springboot.dto.EnrollmentCreateDTO;
import com.tech.springboot.dto.EnrollmentUpdateDTO;
import com.tech.springboot.entity.Enrollment;
import com.tech.springboot.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping()
    public void createEnrollment(@RequestBody EnrollmentCreateDTO enrollmentCreateDTO) {
        enrollmentService.saveEnrollment(enrollmentCreateDTO);
    }

    @PutMapping("/{id}")
    public void updateEnrollment(@PathVariable(value = "id") UUID id
            , @RequestBody EnrollmentUpdateDTO enrollmentUpdateDTO) {
        enrollmentService.updateEnrollment(id, enrollmentUpdateDTO);
    }
}
