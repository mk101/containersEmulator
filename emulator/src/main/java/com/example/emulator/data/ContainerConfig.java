package com.example.emulator.data;

import lombok.Builder;
import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Data
@Builder
public class ContainerConfig {
    private int containerNumber;
    private Calendar startEmulatingTime;
    private double maxTimeShift;
    private double recordingStep;
    private int stepCount;
    private List<SensorConfig> sensors;
    private int sendInterval;
}
