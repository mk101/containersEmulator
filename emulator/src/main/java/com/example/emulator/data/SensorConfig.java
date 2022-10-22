package com.example.emulator.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorConfig {
    private int id;
    private String name;
    private double average;
    private double maximumDispersion;
}
