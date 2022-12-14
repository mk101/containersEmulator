package com.example.processor.service;

import com.example.processor.dto.ContainerDto;
import com.example.processor.mapper.ContainerMapper;
import com.example.processor.model.Container;
import com.example.processor.model.Sensor;
import com.example.processor.repository.ContainerRepository;
import com.example.processor.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContainerService {

    private final ContainerRepository containerRepository;
    private final SensorRepository sensorRepository;
    private final ContainerMapper containerMapper;

    @Transactional
    public Container save(ContainerDto containerDto) {
        var container = containerRepository.save(containerMapper.mapWithoutSensors(containerDto));
        List<Sensor> sensors = new ArrayList<>();

        for (int i = 0; i < containerDto.getSensorsValues().size(); i++) {
            double value = containerDto.getSensorsValues().get(i);

            var sensor = new Sensor();
            sensor.setName(containerDto.getSensorsNames().get(i));
            sensor.setNumeric(value);
            sensor.setMessageId(container.getMessageId());
            sensor = sensorRepository.save(sensor);
            sensors.add(sensor);
        }
        container.setSensors(sensors);

        return container;
    }

}
