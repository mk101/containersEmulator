package com.example.requestmanager.controller;

import com.example.requestmanager.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {RequestController.class})
public class ControllerHandler {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<MessageDto> handleExceptions(RuntimeException e) {
        return ResponseEntity.badRequest().body(new MessageDto(
                e.getMessage()
        ));
    }
}
