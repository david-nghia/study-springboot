package com.tech.springboot.controller;

import com.tech.springboot.lending.api.CourseApi;
import com.tech.springboot.lending.model.CourseRequestDto;
import com.tech.springboot.lending.model.CourseResponseDto;
import com.tech.springboot.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CourseController implements CourseApi {
    private final CourseService courseService;

    @Override
    public ResponseEntity<CourseResponseDto> addCourse(CourseRequestDto courseRequestDto) {
        log.info("Course name: {}", courseRequestDto.getName());
        CourseResponseDto response = courseService.addCourse(courseRequestDto);
        log.info("Add course successful: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteCourseByID(UUID id) {
        log.info("Delete course by id: {}", id);
        courseService.deleteCourseById(id);
        log.info("Delete course successful");
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<CourseResponseDto> getCourseById(UUID id) {
        log.info("Get course by id: {}", id);
        CourseResponseDto response = courseService.getCourseById(id);
        log.info("Get course successful: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<CourseResponseDto>> getCourses() {
        log.info("Get active courses");
        List<CourseResponseDto> courses = courseService.getCourses();
        log.info("Get list of active courses successful");
        return ResponseEntity.ok(courses);
    }

    @Override
    public ResponseEntity<CourseResponseDto> updateCourseByID(UUID id, CourseRequestDto courseRequestDto) {
        log.info("Update course by id: {}", id);
        CourseResponseDto response = courseService.updateCourseById(id, courseRequestDto);
        log.info("Update course successful: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<CourseResponseDto>> searchCourses(Integer offset, Integer limit, List<String> sort, List<String> search) {
        log.info("Search courses based on criteria");
        var response = courseService.searchCourse(offset, limit, sort, search);
        log.info("Search for courses successful");
        return ResponseEntity.ok(response);
    }
}
