package com.tech.springboot.repository;

import com.tech.springboot.entity.Course;
import com.tech.springboot.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Optional<Course> findCourseByName(String name);
    Optional<Course> findCourseById(UUID id);

    List<Course> findCourseByStatus(StatusEnum status);
}
