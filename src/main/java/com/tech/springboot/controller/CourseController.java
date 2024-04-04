package com.tech.springboot.controller;

import com.tech.springboot.dto.CourseResponseDTO;
import com.tech.springboot.dto.base.RestResponseWrapper;
import com.tech.springboot.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping()
    RestResponseWrapper<List<CourseResponseDTO>> getAllCourses() {
        return new RestResponseWrapper<>(courseService.getAllCourses());
    }

}
