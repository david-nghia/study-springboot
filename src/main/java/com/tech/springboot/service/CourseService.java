package com.tech.springboot.service;

import com.tech.springboot.dto.CourseResponseDTO;
import com.tech.springboot.entity.Course;
import com.tech.springboot.mapper.CourseMapper;
import com.tech.springboot.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
