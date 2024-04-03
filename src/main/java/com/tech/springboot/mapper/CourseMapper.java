package com.tech.springboot.mapper;

import com.tech.springboot.dto.CourseResponseDTO;
import com.tech.springboot.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    List<CourseResponseDTO> toDTOs(List<Course> courses);
}
