package com.example.processor.listener;

import com.example.processor.dto.ContainerDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ContainerListener {

    @KafkaListener(containerFactory = "kafkaListenerContainerFactory", topics = "containers")
    public void onMessage(ContainerDto containerDto) {
        System.out.println(containerDto.toString());
    }
}
