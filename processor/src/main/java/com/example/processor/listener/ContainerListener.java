package com.example.processor.listener;

import com.example.processor.dto.ContainerDto;
import com.example.processor.service.ContainerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ContainerListener {

    private final ContainerService containerService;

    @KafkaListener(containerFactory = "kafkaListenerContainerFactory", topics = "containers")
    public void onMessage(ContainerDto containerDto) {
        log.info("New message from kafka: {}", containerService.save(containerDto).toString());
    }
}
