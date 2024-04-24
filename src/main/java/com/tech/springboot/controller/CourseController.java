package com.tech.springboot.controller;

import com.fpt.training.aio.lending.api.CourseApi;
import com.fpt.training.aio.lending.model.CourseRequestDto;
import com.fpt.training.aio.lending.model.CourseResponseDto;
import com.tech.springboot.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class CourseController implements CourseApi {
    private final CourseService courseService;

    @Override
    public ResponseEntity<CourseResponseDto> addCourse(CourseRequestDto courseRequestDto) {
        CourseResponseDto courseResponseDto = courseService.addCourse(courseRequestDto);
        return ResponseEntity.ok(courseResponseDto);
    }

    @Override
    public ResponseEntity<Void> deleteCourseByID(UUID id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<CourseResponseDto> getCourseById(UUID id) {
        CourseResponseDto courseResponseDto = courseService.getCourseById(id);
        return ResponseEntity.ok(courseResponseDto);
    }

    @Override
    public ResponseEntity<List<CourseResponseDto>> getCourses() {
        List<CourseResponseDto> courses = courseService.getCourses();
        return ResponseEntity.ok(courses);
    }

    @Override
    public ResponseEntity<CourseResponseDto> updateCourseByID(UUID id, CourseRequestDto courseRequestDto) {
        CourseResponseDto courseResponseDto = courseService.updateCourseById(id, courseRequestDto);
        return ResponseEntity.ok(courseResponseDto);
    }
}
