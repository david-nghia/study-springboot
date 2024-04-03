package com.tech.springboot.controller;

import com.tech.springboot.dto.CourseResponseDTO;
import com.tech.springboot.entity.Course;
import com.tech.springboot.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping()
    public List<CourseResponseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

}
