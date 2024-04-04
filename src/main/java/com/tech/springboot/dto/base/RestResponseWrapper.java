package com.tech.springboot.dto.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static com.tech.springboot.AppConstants.RESPONSE_SUCCESS_CODE;
import static com.tech.springboot.AppConstants.RESPONSE_SUCCESS_MESSAGE;

public class RestResponseWrapper<T> extends ResponseEntity<ResponseWrapper<T>> {
    public RestResponseWrapper(HttpStatusCode status) {
        super(status);
    }

    public RestResponseWrapper(ResponseWrapper<T> body, HttpStatusCode status) {
        super(body, status);
    }

    public RestResponseWrapper(T data) {
        this(new ResponseWrapper<>(data, RESPONSE_SUCCESS_CODE, RESPONSE_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    public RestResponseWrapper(String message, HttpStatusCode statusCode){
        this(new ResponseWrapper<>(message), statusCode);
    }
}
