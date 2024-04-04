package com.tech.springboot.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class EnrollmentUpdateDTO {
    private UUID id;
    private String status;
}
