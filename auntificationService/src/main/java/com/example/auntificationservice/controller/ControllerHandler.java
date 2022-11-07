package com.example.auntificationservice.controller;

import com.example.auntificationservice.dto.ErrorDto;
import com.example.auntificationservice.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {AuthController.class})
public class ControllerHandler {

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<ErrorDto> handleUserAlreadyExists(UserAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(
                new ErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())
        );
    }
}
