package com.tech.springboot.exception.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlertMessage {
    private String code;
    private String label;

    public static AlertMessage alert(IAlertMessage alert) {
        return AlertMessage.builder()
                .code(alert.getCode())
                .label(alert.getLabel())
                .build();
    }
}
