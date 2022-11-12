package com.example.processor.mapper;

import com.example.processor.dto.ContainerDto;
import com.example.processor.model.Container;
import com.example.processor.model.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

public abstract class ContainerMapperDecorator implements ContainerMapper {

    @Autowired
    @Qualifier("delegate")
    private ContainerMapper delegate;

    @Override
    public Container map(ContainerDto containerDto) {
       Container container = delegate.map(containerDto);

       List<Sensor> sensors = new ArrayList<>();
       List<Double> sensorsValues = containerDto.getSensorsValues();
       List<String> sensorsNames = containerDto.getSensorsNames();
        for (int i = 0; i < sensorsValues.size(); i++) {
            double value = sensorsValues.get(i);

            var sensor = new Sensor();
            sensor.setName(sensorsNames.get(i));
            sensor.setNumeric(value);
            sensor.setMessageId(container.getMessageId());

            sensors.add(sensor);
        }
       container.setSensors(sensors);

       return container;
    }
}
