package com.tech.springboot.service;

import com.fpt.training.aio.lending.model.CourseRequestDto;
import com.fpt.training.aio.lending.model.CourseResponseDto;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    CourseResponseDto addCourse(CourseRequestDto courseRequestDto);

    List<CourseResponseDto> getCourses();

    CourseResponseDto updateCourseById(UUID uuid, CourseRequestDto courseRequestDto);

    CourseResponseDto getCourseById(UUID uuid);

    void deleteCourseById(UUID uuid);


}
