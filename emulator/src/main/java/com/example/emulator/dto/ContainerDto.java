package com.example.emulator.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ContainerDto {
    private int containerNumber;
    private Date timeStamp;
    private List<Double> sensorsValues;
}
