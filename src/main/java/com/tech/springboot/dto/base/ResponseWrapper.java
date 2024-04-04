package com.tech.springboot.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseWrapper<T> {
    private T data;
    private Integer status;
    private String message;

    public ResponseWrapper(T data, Integer status) {
        this.data = data;
        this.status = status;
    }

    public ResponseWrapper(String message) {
        this.message = message;
    }
}
