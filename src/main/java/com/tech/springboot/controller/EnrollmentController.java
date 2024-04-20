package com.tech.springboot.controller;

import com.fpt.training.aio.lending.api.EnrollmentApi;
import com.fpt.training.aio.lending.model.EnrollmentRequestDto;
import com.tech.springboot.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class EnrollmentController implements EnrollmentApi {
    @Autowired
    private EnrollmentService enrollmentService;

    @Override
    public ResponseEntity<EnrollmentRequestDto> createEnrollment(EnrollmentRequestDto enrollmentRequestDto) {
        var response = enrollmentService.saveEnrollment(enrollmentRequestDto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<EnrollmentRequestDto> updateEnrollment(UUID id, EnrollmentRequestDto enrollmentRequestDto) {
        var response = enrollmentService.updateEnrollment(id, enrollmentRequestDto);
        return ResponseEntity.ok(response);
    }
}
