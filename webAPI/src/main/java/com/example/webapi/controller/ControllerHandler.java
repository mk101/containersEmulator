package com.example.webapi.controller;


import com.example.webapi.dto.ErrorDto;
import com.example.webapi.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {WebController.class})
public class ControllerHandler {

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<ErrorDto> catchAuthException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErrorDto(HttpStatus.UNAUTHORIZED, e.getMessage())
        );
    }
}
