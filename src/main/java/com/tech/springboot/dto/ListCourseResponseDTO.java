package com.tech.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListCourseResponseDTO {
    private List<CourseResponseDTO> courses;
    private Meta meta;
}
