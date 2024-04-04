package com.tech.springboot.service;

import com.tech.springboot.dto.CourseResponseDTO;
import com.tech.springboot.entity.Course;
import com.tech.springboot.mapper.CourseMapper;
import com.tech.springboot.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<CourseResponseDTO> getAllCourses(){
        return buildListCourseResponse(courseRepository.findAll());
    }

    private List<CourseResponseDTO> buildListCourseResponse(List<Course> courses){
        return CourseMapper.INSTANCE.toDTOs(courses);
    }
}
