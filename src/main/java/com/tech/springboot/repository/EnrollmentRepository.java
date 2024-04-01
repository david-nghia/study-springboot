package com.tech.springboot.repository;

import com.tech.springboot.entity.Enrollment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
    @Transactional
    @Query("SELECT e FROM Enrollment e WHERE e.id = :userId")
    Enrollment findByEnrollmentId(@Param("userId") String userId);
}
