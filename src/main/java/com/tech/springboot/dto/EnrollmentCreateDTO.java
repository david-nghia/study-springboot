package com.tech.springboot.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class EnrollmentCreateDTO {
    private UUID userId;
    private UUID courseId;
    private String status;
    private Date enrollmentDate;
    private Date completionDate;
    private String note;
}
