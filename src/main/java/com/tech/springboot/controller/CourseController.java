package com.tech.springboot.controller;

import com.tech.springboot.dto.ListCourseResponseDTO;
import com.tech.springboot.dto.base.RestResponseWrapper;
import com.tech.springboot.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping()
    RestResponseWrapper<ListCourseResponseDTO> getAllCourses(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "5") int limit) {
        return new RestResponseWrapper<>(courseService.getAllCourses(offset, limit));
    }

}
