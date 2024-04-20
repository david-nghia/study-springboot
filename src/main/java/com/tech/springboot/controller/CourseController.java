package com.tech.springboot.controller;

import com.fpt.training.aio.lending.api.CourseApi;
import com.fpt.training.aio.lending.model.CourseResponseDto;
import com.tech.springboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController implements CourseApi {
    @Autowired
    private CourseService courseService;

    @Override
    public ResponseEntity<List<CourseResponseDto>> getCourses() {
        List<CourseResponseDto> courses = courseService.getCourses();
        return ResponseEntity.ok(courses);
    }
}
