package com.tech.springboot.service.impl;

import com.fpt.training.aio.lending.model.CourseRequestDto;
import com.fpt.training.aio.lending.model.CourseResponseDto;
import com.tech.springboot.entity.Course;
import com.tech.springboot.mapper.CourseMapper;
import com.tech.springboot.repository.CourseRepository;
import com.tech.springboot.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public CourseResponseDto addCourse(CourseRequestDto courseRequestDto) {
        return null;
    }

    @Override
    public List<CourseResponseDto> getCourses() {
        return null;
    }

    @Override
    public CourseResponseDto updateCourseById(UUID uuid, CourseRequestDto courseRequestDto) {
        return null;
    }

    @Override
    public CourseResponseDto getCourseById(UUID uuid) {
        return null;
    }

    @Override
    public void deleteCourseById(UUID uuid) {

    }
}
