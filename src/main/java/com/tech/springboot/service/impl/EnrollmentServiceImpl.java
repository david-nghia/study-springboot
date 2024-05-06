package com.tech.springboot.service.impl;

import com.tech.springboot.constant.GlobalFunction;
import com.tech.springboot.lending.model.EnrollmentRequestDto;
import com.tech.springboot.lending.model.EnrollmentResponseDto;
import com.tech.springboot.mapper.UserMapper;
import com.tech.springboot.model.entity.Enrollment;
import com.tech.springboot.enums.ExceptionAlertEnum;
import com.tech.springboot.enums.StatusEnum;
import com.tech.springboot.exception.BusinessException;
import com.tech.springboot.exception.dto.AlertMessage;
import com.tech.springboot.mapper.EnrollmentMapper;
import com.tech.springboot.model.entity.User;
import com.tech.springboot.repository.EnrollmentRepository;
import com.tech.springboot.service.EnrollmentService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final EntityManager entityManager;


    @Override
    public EnrollmentResponseDto addEnrollment(EnrollmentRequestDto enrollmentRequestDto) {
        Enrollment enrollment = EnrollmentMapper.INSTANCE.toEntity(enrollmentRequestDto);
        enrollmentRepository.save(enrollment);
        return EnrollmentMapper.INSTANCE.toDto(enrollment);
    }

    @Override
    public List<EnrollmentResponseDto> getEnrollments() {
        List<Enrollment> activeEnrollments = enrollmentRepository.findEnrollmentByStatus(StatusEnum.ACTIVE);
        if (activeEnrollments.isEmpty()) {
            return Collections.emptyList();
        }
        List<EnrollmentResponseDto> enrollments = EnrollmentMapper.INSTANCE.toDtoList(activeEnrollments);
        return enrollments;
    }

    @Override
    public EnrollmentResponseDto updateEnrollmentById(UUID uuid, EnrollmentRequestDto enrollmentRequestDto) {
        Optional<Enrollment> enrollmentOpt = enrollmentRepository.findEnrollmentById(uuid);
        if (enrollmentOpt.isEmpty()) {
            throw new BusinessException("Enrollment does not exist",
                    AlertMessage.alert(ExceptionAlertEnum.ENROLLMENT_EXIST_DB));
        }
        Enrollment enrollmentToUpdate = enrollmentOpt.get();
        if (enrollmentToUpdate.getStatus() != StatusEnum.ACTIVE) {
            throw new BusinessException("Enrollment is non-active",
                    AlertMessage.alert(ExceptionAlertEnum.ENROLLMENT_NON_ACTIVE));
        }
        enrollmentToUpdate.setNote(enrollmentRequestDto.getNote());
        enrollmentToUpdate.setInstructor(enrollmentToUpdate.getInstructor());
        enrollmentRepository.save(enrollmentToUpdate);
        return EnrollmentMapper.INSTANCE.toDto(enrollmentToUpdate);
    }

    @Override
    public EnrollmentResponseDto getEnrollmentById(UUID uuid) {
        Optional<Enrollment> enrollmentOpt = enrollmentRepository.findEnrollmentById(uuid);
        if (enrollmentOpt.isEmpty()) {
            throw new BusinessException("Enrollment does not exist",
                    AlertMessage.alert(ExceptionAlertEnum.ENROLLMENT_EXIST_DB));
        }
        Enrollment enrollment = enrollmentOpt.get();
        if (enrollment.getStatus() != StatusEnum.ACTIVE) {
            throw new BusinessException("Enrollment is non-active",
                    AlertMessage.alert(ExceptionAlertEnum.ENROLLMENT_NON_ACTIVE));
        }
        return EnrollmentMapper.INSTANCE.toDto(enrollment);
    }

    @Override
    public void deleteEnrollmentById(UUID uuid) {
        Optional<Enrollment> enrollmentOpt = enrollmentRepository.findEnrollmentById(uuid);
        if (enrollmentOpt.isEmpty()) {
            throw new BusinessException("Enrollment does not exist",
                    AlertMessage.alert(ExceptionAlertEnum.ENROLLMENT_EXIST_DB));
        }
        Enrollment enrollmentToDelete = enrollmentOpt.get();
        if (enrollmentToDelete.getStatus() != StatusEnum.ACTIVE) {
            throw new BusinessException("Enrollment is non-active",
                    AlertMessage.alert(ExceptionAlertEnum.ENROLLMENT_NON_ACTIVE));
        }
        enrollmentToDelete.setStatus(StatusEnum.DELETED);
        enrollmentRepository.save(enrollmentToDelete);
    }

    @Override
    public List<EnrollmentResponseDto> searchEnrollment(Integer offset, Integer limit, List<String> sort, List<String> search) {
        List<?> rawEnrollments = GlobalFunction.filter(entityManager, Enrollment.class, offset, limit, sort, search);
        List<Enrollment> enrollments = null;

        if (rawEnrollments != null && rawEnrollments.size() > 0 && rawEnrollments.get(0) instanceof Enrollment) {
            enrollments = (List<Enrollment>) rawEnrollments;
        } else {
//            throw new IllegalArgumentException("The result is not of type List<Enrollment>");
            enrollments = Collections.emptyList();
        }
        return EnrollmentMapper.INSTANCE.toDtoList(enrollments);
    }
}
