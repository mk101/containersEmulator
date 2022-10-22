package com.example.requestmanager.controller;

import com.example.requestmanager.dto.ContainerDto;
import com.example.requestmanager.dto.MessageDto;
import com.example.requestmanager.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    private final ProducerService producerService;

    @Autowired
    public RequestController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/")
    public MessageDto post(@RequestBody ContainerDto container) {
        producerService.sendToKafka(container);
        return new MessageDto(HttpStatus.OK, "");
    }
}
