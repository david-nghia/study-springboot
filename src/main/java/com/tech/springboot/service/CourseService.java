package com.tech.springboot.service;

import com.tech.springboot.dto.CourseResponseDTO;
import com.tech.springboot.dto.ListCourseResponseDTO;
import com.tech.springboot.dto.Meta;
import com.tech.springboot.entity.Course;
import com.tech.springboot.mapper.CourseMapper;
import com.tech.springboot.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public ListCourseResponseDTO getAllCourses(int offset, int limit) {
        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by("courseName"));
        Page<Course> coursePage = courseRepository.findAll(pageRequest);
        return buildListCourseResponse(coursePage, offset, limit);
    }

    private ListCourseResponseDTO buildListCourseResponse(Page<Course> coursePage, int offset, int limit) {
        List<CourseResponseDTO> courseResponseDTOS = CourseMapper.INSTANCE.toDTOs(coursePage.getContent());

        Meta meta = new Meta(coursePage.getNumberOfElements(), limit, offset,
                (int) coursePage.getTotalElements());
        return ListCourseResponseDTO.builder()
                .courses(courseResponseDTOS)
                .meta(meta)
                .build();
    }
}
