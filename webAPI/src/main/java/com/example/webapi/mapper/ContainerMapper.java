package com.example.webapi.mapper;

import com.example.webapi.dto.ContainerDto;
import com.example.webapi.dto.SensorDto;
import com.example.webapi.model.Container;
import com.example.webapi.model.Sensor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContainerMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "value", source = "numeric")
    SensorDto map(Sensor sensor);

    @Mapping(target = "number", source = "number")
    @Mapping(target = "time", source = "timestamp")
    @Mapping(target = "sensors", source = "sensors")
    ContainerDto map(Container container);
}
