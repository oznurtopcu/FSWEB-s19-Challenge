package com.workintech.s19challenge.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    //Slf4j log atmak için kullanılıyor

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleApiException(ApiException apiException) {
        //hata detaylarını görebilmek için logladık
        log.error("ApiException occured: " + apiException);
        ExceptionResponse apiErrorResponse = new ExceptionResponse(apiException.getMessage(), apiException.getHttpStatus().value(), LocalDateTime.now());
        return new ResponseEntity<>(apiErrorResponse, apiException.getHttpStatus());
    }


    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        log.error("Exception occured: " + exception);
        ExceptionResponse errorResponse = new ExceptionResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
