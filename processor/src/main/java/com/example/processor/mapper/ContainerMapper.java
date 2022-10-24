package com.example.processor.mapper;

import com.example.processor.dto.ContainerDto;
import com.example.processor.model.Container;
import org.mapstruct.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Mapper(componentModel = "spring")
@DecoratedWith(ContainerMapperDecorator.class)
public interface ContainerMapper {
    @Mapping(target = "number", source = "containerNumber")
    @Mapping(target = "timestamp", source = "timeStamp")
    Container map(ContainerDto containerDto);

    @Mapping(target = "number", source = "containerNumber")
    @Mapping(target = "timestamp", source = "timeStamp")
    Container mapWithoutSensors(ContainerDto containerDto);

    default Timestamp map(String timestamp) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return new Timestamp(sdf.parse(timestamp).getTime());
    }
}
