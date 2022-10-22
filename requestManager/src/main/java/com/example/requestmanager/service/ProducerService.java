package com.example.requestmanager.service;

import com.example.requestmanager.dto.ContainerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    private final KafkaTemplate<Integer, ContainerDto> kafkaTemplate;

    @Autowired
    public ProducerService(KafkaTemplate<Integer, ContainerDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendToKafka(ContainerDto containerDto) {
        kafkaTemplate.send("containers", containerDto.getContainerNumber(), containerDto);
    }
}
