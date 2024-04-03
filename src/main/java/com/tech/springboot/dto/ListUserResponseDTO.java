package com.tech.springboot.dto;

import com.tech.springboot.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListUserResponseDTO {
    private List<User> users;
}
