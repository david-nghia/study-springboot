package com.tech.springboot.exception;

import com.tech.springboot.exception.dto.AlertMessage;

public class BusinessException extends BaseException{
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(AlertMessage alertMessage) {
        super(alertMessage);
    }

    public BusinessException(String message, AlertMessage alertMessage) {
        super(message, alertMessage);
    }
}
