package com.example.emulator.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SensorConfig {
    private int id;
    private String name;
    private double average;
    private double maximumDispersion;
}
