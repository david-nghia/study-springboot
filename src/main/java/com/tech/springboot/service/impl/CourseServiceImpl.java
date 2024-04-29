package com.tech.springboot.service.impl;

import com.fpt.training.aio.lending.model.CourseRequestDto;
import com.fpt.training.aio.lending.model.CourseResponseDto;
import com.tech.springboot.entity.Course;
import com.tech.springboot.enums.ExceptionAlertEnum;
import com.tech.springboot.enums.StatusEnum;
import com.tech.springboot.exception.BusinessException;
import com.tech.springboot.exception.dto.AlertMessage;
import com.tech.springboot.mapper.CourseMapper;
import com.tech.springboot.repository.CourseRepository;
import com.tech.springboot.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public CourseResponseDto addCourse(CourseRequestDto courseRequestDto) {
        Optional<Course> courseOpt = courseRepository.findCourseByName(courseRequestDto.getName());
        if (!courseOpt.isEmpty()) {
            throw new BusinessException("Add exist course",
                    AlertMessage.alert(ExceptionAlertEnum.COURSE_EXIST_DB));
        }
        Course course = courseOpt.get();
        Course courseDb = courseRepository.save(course);
        return CourseMapper.INSTANCE.toDto(courseDb);
    }

    @Override
    public List<CourseResponseDto> getCourses() {
        List<Course> activeCourses = courseRepository.findCourseByStatus(StatusEnum.ACTIVE);
        if (activeCourses.isEmpty()) {
            return Collections.emptyList();
        }
        List<CourseResponseDto> courses = CourseMapper.INSTANCE.toDtoList(activeCourses);
        return courses;
    }

    @Override
    public CourseResponseDto updateCourseById(UUID uuid, CourseRequestDto courseRequestDto) {
        Optional<Course> courseOpt = courseRepository.findCourseById(uuid);
        if (courseOpt.isEmpty()) {
            throw new BusinessException("Course does not exist",
                    AlertMessage.alert(ExceptionAlertEnum.COURSE_NOT_FOUND));
        }
        Course courseToUpdate = courseOpt.get();
        if (courseToUpdate.getStatus() != StatusEnum.ACTIVE) {
            throw new BusinessException("Course is non-active",
                    AlertMessage.alert(ExceptionAlertEnum.COURSE_NON_ACTIVE));
        }
        courseToUpdate.setName(courseRequestDto.getName());
        courseToUpdate.setDescription(courseRequestDto.getDescription());
        courseToUpdate.setStartDate(OffsetDateTime.parse(courseRequestDto.getStartDate()));
        courseToUpdate.setEndDate(OffsetDateTime.parse(courseRequestDto.getEndDate()));
        courseToUpdate.setDuration(Duration.parse(String.valueOf(courseRequestDto.getDuration())));
        courseToUpdate.setTuitionFee(BigDecimal.valueOf(courseRequestDto.getTuitionFee()));
        courseRepository.save(courseToUpdate);
        return CourseMapper.INSTANCE.toDto(courseToUpdate);
    }

    @Override
    public CourseResponseDto getCourseById(UUID uuid) {
        Optional<Course> courseOpt = courseRepository.findCourseById(uuid);
        if (courseOpt.isEmpty()) {
            throw new BusinessException("Course does not exist",
                    AlertMessage.alert(ExceptionAlertEnum.COURSE_NOT_FOUND));
        }
        Course course = courseOpt.get();
        if (course.getStatus() != StatusEnum.ACTIVE) {
            throw new BusinessException("Course is non-active",
                    AlertMessage.alert(ExceptionAlertEnum.COURSE_NON_ACTIVE));
        }
        return CourseMapper.INSTANCE.toDto(course);
    }

    @Override
    public void deleteCourseById(UUID uuid) {
        Optional<Course> courseOpt = courseRepository.findCourseById(uuid);
        if (courseOpt.isEmpty()) {
            throw new BusinessException("Course does not exist",
                    AlertMessage.alert(ExceptionAlertEnum.COURSE_NOT_FOUND));
        }
        Course courseToDelete = courseOpt.get();
        if (courseToDelete.getStatus() != StatusEnum.ACTIVE) {
            throw new BusinessException("Course is non-active",
                    AlertMessage.alert(ExceptionAlertEnum.COURSE_NON_ACTIVE));
        }
        courseToDelete.setStatus(StatusEnum.DELETED);
        courseRepository.save(courseToDelete);
    }
}
