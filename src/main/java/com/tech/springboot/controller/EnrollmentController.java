package com.tech.springboot.controller;

import com.tech.springboot.lending.api.EnrollmentApi;
import com.tech.springboot.lending.model.EnrollmentRequestDto;
import com.tech.springboot.lending.model.EnrollmentResponseDto;
import com.tech.springboot.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequiredArgsConstructor
@Slf4j
public class EnrollmentController implements EnrollmentApi {
    private final EnrollmentService enrollmentService;

    @Override
    public ResponseEntity<EnrollmentResponseDto> addEnrollment(EnrollmentRequestDto enrollmentRequestDto) {
        log.info("Add new enrollment");
        EnrollmentResponseDto response = enrollmentService.addEnrollment(enrollmentRequestDto);
        log.info("Add to enrollment successful: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteEnrollmentByID(UUID id) {
        log.info("Delete enrollment by id: {}", id);
        enrollmentService.deleteEnrollmentById(id);
        log.info("Delete enrollment successful");
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<EnrollmentResponseDto> getEnrollmentById(UUID id) {
        log.info("Get enrollment by id: {}", id);
        EnrollmentResponseDto response = enrollmentService.getEnrollmentById(id);
        log.info("Get enrollment successful: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<EnrollmentResponseDto>> getEnrollments() {
        log.info("Get active enrollments");
        List<EnrollmentResponseDto> enrollments = enrollmentService.getEnrollments();
        log.info("Get list of active enrollments successful");
        return ResponseEntity.ok(enrollments);
    }

    @Override
    public ResponseEntity<EnrollmentResponseDto> updateEnrollmentByID(UUID id, EnrollmentRequestDto enrollmentRequestDto) {
        log.info("Update enrollment by id: {}", id);
        EnrollmentResponseDto response = enrollmentService.updateEnrollmentById(id, enrollmentRequestDto);
        log.info("Update enrollment successful: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<EnrollmentResponseDto>> searchEnrollments(Integer offset, Integer limit, List<String> sort, List<String> search) {
        log.info("Search enrollments based on criteria");
        var response = enrollmentService.searchEnrollment(offset, limit, sort, search);
        log.info("Search for enrollments successful");
        return ResponseEntity.ok(response);
    }
}
