package com.tech.springboot.service.impl;

import com.fpt.training.aio.lending.model.CourseResponseDto;
import com.tech.springboot.entity.Course;
import com.tech.springboot.mapper.CourseMapper;
import com.tech.springboot.repository.CourseRepository;
import com.tech.springboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public List<CourseResponseDto> getCourses() {
        List<Course> courses = courseRepository.findAll();
        return CourseMapper.INSTANCE.toDTOs(courses);
    }
}
