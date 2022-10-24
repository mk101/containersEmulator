package com.example.processor.listener;

import com.example.processor.dto.ContainerDto;
import com.example.processor.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ContainerListener {

    private final ContainerService containerService;

    @Autowired
    public ContainerListener(ContainerService containerService) {
        this.containerService = containerService;
    }

    @KafkaListener(containerFactory = "kafkaListenerContainerFactory", topics = "containers")
    public void onMessage(ContainerDto containerDto) {
        System.out.println(containerService.save(containerDto).toString()); //TODO: add logger
    }
}
