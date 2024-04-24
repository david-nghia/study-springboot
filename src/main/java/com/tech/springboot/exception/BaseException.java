package com.tech.springboot.exception;

import com.tech.springboot.exception.dto.AlertMessage;
import lombok.Getter;

import java.io.Serial;

@Getter
public class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7301891288391326310L;
    private String message;
    private AlertMessage alertMessage;

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(AlertMessage alertMessage) {
        this.alertMessage = alertMessage;
    }

    public BaseException(String message, AlertMessage alertMessage) {
        super(message);
        this.message = message;
        this.alertMessage = alertMessage;
    }
}
