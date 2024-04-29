package com.tech.springboot.service.impl;

import com.fpt.training.aio.lending.model.EnrollmentRequestDto;
import com.fpt.training.aio.lending.model.EnrollmentResponseDto;
import com.tech.springboot.entity.Enrollment;
import com.tech.springboot.enums.ExceptionAlertEnum;
import com.tech.springboot.enums.StatusEnum;
import com.tech.springboot.exception.BusinessException;
import com.tech.springboot.exception.dto.AlertMessage;
import com.tech.springboot.mapper.EnrollmentMapper;
import com.tech.springboot.repository.EnrollmentRepository;
import com.tech.springboot.service.EnrollmentService;
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
}
