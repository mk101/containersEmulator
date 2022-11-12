package com.example.webapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerDto {
    private Integer number;
    private Timestamp time;
    private List<SensorDto> sensors;
}
