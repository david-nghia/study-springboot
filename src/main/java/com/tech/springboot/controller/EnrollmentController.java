package com.tech.springboot.controller;

import com.fpt.training.aio.lending.api.EnrollmentApi;
import com.fpt.training.aio.lending.model.EnrollmentRequestDto;
import com.fpt.training.aio.lending.model.EnrollmentResponseDto;
import com.tech.springboot.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class EnrollmentController implements EnrollmentApi {
    private final EnrollmentService enrollmentService;

    @Override
    public ResponseEntity<EnrollmentResponseDto> addEnrollment(EnrollmentRequestDto enrollmentRequestDto) {
        EnrollmentResponseDto response = enrollmentService.addEnrollment(enrollmentRequestDto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteEnrollmentByID(UUID id) {
        enrollmentService.deleteEnrollmentById(id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<EnrollmentResponseDto> getEnrollmentById(UUID id) {
        EnrollmentResponseDto response = enrollmentService.getEnrollmentById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<EnrollmentResponseDto>> getEnrollments() {
        List<EnrollmentResponseDto> enrollments = enrollmentService.getEnrollments();
        return ResponseEntity.ok(enrollments);
    }

    @Override
    public ResponseEntity<EnrollmentResponseDto> updateEnrollmentByID(UUID id, EnrollmentRequestDto enrollmentRequestDto) {
        EnrollmentResponseDto response = enrollmentService.updateEnrollmentById(id, enrollmentRequestDto);
        return ResponseEntity.ok(response);
    }
}
