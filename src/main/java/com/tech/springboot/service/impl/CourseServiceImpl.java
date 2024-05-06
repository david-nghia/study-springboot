package com.tech.springboot.service.impl;

import com.tech.springboot.constant.GlobalFunction;
import com.tech.springboot.lending.model.CourseRequestDto;
import com.tech.springboot.lending.model.CourseResponseDto;
import com.tech.springboot.mapper.UserMapper;
import com.tech.springboot.model.entity.Course;
import com.tech.springboot.enums.ExceptionAlertEnum;
import com.tech.springboot.enums.StatusEnum;
import com.tech.springboot.exception.BusinessException;
import com.tech.springboot.exception.dto.AlertMessage;
import com.tech.springboot.mapper.CourseMapper;
import com.tech.springboot.model.entity.User;
import com.tech.springboot.repository.CourseRepository;
import com.tech.springboot.service.CourseService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
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
    private final EntityManager entityManager;

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

    @Override
    public List<CourseResponseDto> searchCourse(Integer offset, Integer limit, List<String> sort, List<String> search) {
        List<?> rawCourses = GlobalFunction.filter(entityManager, Course.class, offset, limit, sort, search);
        List<Course> courses = null;

        if (rawCourses != null && rawCourses.size() > 0 && rawCourses.get(0) instanceof Course) {
            courses = (List<Course>) rawCourses;
        } else {
//            throw new IllegalArgumentException("The result is not of type List<User>");
            courses = Collections.emptyList();
        }
        return CourseMapper.INSTANCE.toDtoList(courses);
    }
}
