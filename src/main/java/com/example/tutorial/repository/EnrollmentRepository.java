package com.example.tutorial.repository;

import com.example.tutorial.model.Enrollment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    @Transactional
    @Query("SELECT e FROM Enrollment e WHERE e.id = :userId")
    Enrollment findByEnrollmentId(@Param("userId") int userId);
}
