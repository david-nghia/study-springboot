package com.tech.springboot.controller;

import com.tech.springboot.dto.EnrollmentCreateDTO;
import com.tech.springboot.dto.EnrollmentUpdateDTO;
import com.tech.springboot.dto.base.RestResponseWrapper;
import com.tech.springboot.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
//@Tag(name = "enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Operation(
            description = "Post endpoint for enrollment",
            summary = "This is a summary for enrollment post endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "UnAuthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Internal server error",
                            responseCode = "500"
                    ),
            }
    )

    @PostMapping()
    public RestResponseWrapper<?> createEnrollment(@RequestBody EnrollmentCreateDTO enrollmentCreateDTO) {
        try {
            enrollmentService.saveEnrollment(enrollmentCreateDTO);
            return new RestResponseWrapper<>("Enrollment created successfully",
                    HttpStatusCode.valueOf(HttpStatus.OK.value()));
        } catch (Exception e) {
            return new RestResponseWrapper<>("Failed to create Enrollment",
                    HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    @Operation(
            description = "Put endpoint for enrollment",
            summary = "This is a summary for enrollment put endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "UnAuthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Internal server error",
                            responseCode = "500"
                    ),
            }
    )

    @PutMapping("/{id}")
    public RestResponseWrapper<?> updateEnrollment(@PathVariable(value = "id") UUID id
            , @RequestBody EnrollmentUpdateDTO enrollmentUpdateDTO) {
        try {
            enrollmentService.updateEnrollment(id, enrollmentUpdateDTO);
            return new RestResponseWrapper<>("Enrollment updated successfully",
                    HttpStatusCode.valueOf(HttpStatus.OK.value()));
        } catch (Exception e) {
            return new RestResponseWrapper<>("Failed to update Enrollment",
                    HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }
}
