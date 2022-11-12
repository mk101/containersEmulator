package com.example.requestmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerDto {
    private int containerNumber;
    private String timeStamp;
    private List<Double> sensorsValues;
    private List<String> sensorsNames;
}
