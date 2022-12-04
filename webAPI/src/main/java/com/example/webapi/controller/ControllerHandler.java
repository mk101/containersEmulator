package com.example.webapi.controller;


import com.example.webapi.dto.ErrorDto;
import com.example.webapi.exception.AuthenticationException;
import com.example.webapi.exception.InvalidAttributeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {WebController.class})
public class ControllerHandler {

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<ErrorDto> catchAuthException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErrorDto(e.getMessage())
        );
    }

    @ExceptionHandler(value = {InvalidAttributeException.class})
    public ResponseEntity<ErrorDto> catchAttributeException(InvalidAttributeException e) {
        return ResponseEntity.badRequest().body(
                new ErrorDto(e.getMessage())
        );
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ErrorDto> catchRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorDto(e.getMessage())
        );
    }
}
