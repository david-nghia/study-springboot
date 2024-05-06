package com.tech.springboot.repository;

import com.tech.springboot.model.entity.Enrollment;
import com.tech.springboot.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
    Optional<Enrollment> findEnrollmentById(UUID id);

    List<Enrollment> findEnrollmentByStatus(StatusEnum status);
}
