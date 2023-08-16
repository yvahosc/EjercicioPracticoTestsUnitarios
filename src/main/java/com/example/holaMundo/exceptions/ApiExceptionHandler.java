package com.example.holaMundo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestExeption.class})
    public  ResponseEntity<ApiException> handleNotValueExeption(ApiRequestExeption e){
        ApiException exception = new ApiException(e.getStatusCode(), e.getMessage());
        return new ResponseEntity<ApiException>(exception, HttpStatus.NOT_FOUND);
    }
}
