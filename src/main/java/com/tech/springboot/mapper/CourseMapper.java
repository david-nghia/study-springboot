package com.tech.springboot.mapper;

import com.tech.springboot.lending.model.CourseResponseDto;
import com.tech.springboot.model.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    @Mapping(source = "modifiedDate", target = "modifiedDate", qualifiedByName = "offsetDatetimeToString")
    @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "offsetDatetimeToString")
    @Mapping(source = "startDate", target = "startDate", qualifiedByName = "offsetDatetimeToString")
    @Mapping(source = "endDate", target = "endDate", qualifiedByName = "offsetDatetimeToString")
    @Mapping(source = "duration", target = "duration", qualifiedByName = "durationToInteger")
    CourseResponseDto toDto(Course course);
    List<CourseResponseDto> toDtoList(List<Course> courses);

    @Named("offsetDatetimeToString")
    default String offsetDatetimeToString(OffsetDateTime offsetDateTime){
        return offsetDateTime.toString();
    }

    @Named("durationToInteger")
    default Integer durationToInteger(Duration duration){
        return Integer.parseInt(duration.toString());
    }
}
