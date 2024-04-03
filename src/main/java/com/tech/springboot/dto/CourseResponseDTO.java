package com.tech.springboot.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class CourseResponseDTO {
    private UUID id;
    private String courseName;
    private String courseDescription;
    private Date startDate;
    private Date endDate;
    private Integer duration;
    private Integer price;
}
