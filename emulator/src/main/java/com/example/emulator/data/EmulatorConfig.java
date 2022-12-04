package com.example.emulator.data;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class EmulatorConfig {
    private int containersCount;
    private Date startEmulatingTime;
    private double maxTimeShift;
    private double recordingStep;
    private int stepCount;
    private List<SensorConfig> sensors;
    private int sendInterval;
    private String requestManagerUrl;
}
