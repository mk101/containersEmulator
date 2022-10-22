package com.example.requestmanager.controller;

import com.example.requestmanager.dto.ContainerDto;
import com.example.requestmanager.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    @PostMapping("/")
    public MessageDto post(@RequestBody ContainerDto container) {
        System.out.println(container.toString());
        return new MessageDto(HttpStatus.OK, "");
    }
}
