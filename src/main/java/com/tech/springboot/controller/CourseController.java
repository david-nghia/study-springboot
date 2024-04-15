package com.tech.springboot.controller;

import com.tech.springboot.dto.ListCourseResponseDTO;
import com.tech.springboot.dto.base.RestResponseWrapper;
import com.tech.springboot.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    @Operation(
            description = "Get endpoint for course",
            summary = "This is a summary for course get endpoint",
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
    @GetMapping()
    RestResponseWrapper<ListCourseResponseDTO> getAllCourses(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "5") int limit) {
        return new RestResponseWrapper<>(courseService.getAllCourses(offset, limit));
    }

}
