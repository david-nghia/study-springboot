package com.tech.springboot.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
public class UserResponseDTO {
    private UUID id;
    private String username;
    private String email;
    private Date dateOfBirth;
}
