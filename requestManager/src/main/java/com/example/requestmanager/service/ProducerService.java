package com.example.requestmanager.service;

import com.example.requestmanager.dto.ContainerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {
    private final KafkaTemplate<Integer, ContainerDto> kafkaTemplate;

    public void sendToKafka(ContainerDto containerDto) {
        kafkaTemplate.send("containers", containerDto.getContainerNumber(), containerDto);
    }
}
