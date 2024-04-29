//package com.tech.springboot.handler;
//
//import com.fpt.training.aio.lending.model.Error;
//import com.fpt.training.aio.lending.model.ErrorDetails;
//import com.tech.springboot.exception.BusinessException;
//import com.tech.springboot.exception.dto.AlertMessage;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.time.LocalDateTime;
//import java.util.Collections;
//import java.util.Locale;
//
//@ControllerAdvice
//@Slf4j
//@AllArgsConstructor
//public class GlobalExceptionHandler {
//
//    @Autowired
//    private final MessageSource businessMessages;
//
//    @ExceptionHandler(BusinessException.class)
//    public ResponseEntity<Error> handleBusinessException(BusinessException ex, Locale locale) {
//        // get message from resource
//        AlertMessage alertMessage = ex.getAlertMessage();
//
//        String message = "";
//        try {
//            message = this.businessMessages
//                .getMessage(alertMessage.getLabel(), null, locale);
//        } catch (Exception ex1) {
//            log.error("Err when get message resource {}", ex1.getMessage(), ex1);
//        }
//
//        ErrorDetails errorDetail = ErrorDetails.builder()
//            .message(message)
//            .code(alertMessage.getCode())
//            .build();
//
//        Error error = Error.builder()
//            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//            .timestamp(LocalDateTime.now().toString())
//            .message(ex.getMessage())
//            .error(Collections.singletonList(errorDetail))
//            .build();
//
//        log.error(ex.getMessage(), ex);
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//    }
//
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Error> handleCommonException(Exception ex) {
//        Error error = Error.builder()
//            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//            .timestamp(LocalDateTime.now().toString())
//            .message(ex.getCause().toString())
//            .build();
//
//        log.error(ex.getMessage(), ex);
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//    }
//}