package com.tech.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meta implements Serializable {
    private Integer count;
    private Integer limit;
    private Integer offset;
    private Integer totalItem;
}
