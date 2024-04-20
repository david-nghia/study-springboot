package com.tech.springboot.service;

import com.fpt.training.aio.lending.model.CourseResponseDto;

import java.util.List;

public interface CourseService {
    List<CourseResponseDto> getCourses();
}
