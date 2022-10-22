package com.example.emulator.service;

import com.example.emulator.data.ContainerConfig;
import com.example.emulator.data.EmulatorConfig;
import com.example.emulator.runnable.ContainerRunnable;
import lombok.Getter;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EmulatorService {
    @Getter
    private final EmulatorConfig config;
    private final ParserService parserService;
    private final SendService sendService;

    public EmulatorService(EmulatorConfig config, ParserService parserService, SendService sendService) {
        this.config = config;
        this.parserService = parserService;
        this.sendService = sendService;
    }

    public void start() {
        Calendar time = new GregorianCalendar();
        time.setTime(config.getStartEmulatingTime());

        for (int i = 0; i < config.getContainersCount(); i++) {
            ContainerConfig containerConfig = ContainerConfig.builder()
                    .containerNumber(i+1)
                    .startEmulatingTime(time)
                    .maxTimeShift(config.getMaxTimeShift())
                    .recordingStep(config.getRecordingStep())
                    .stepCount(config.getStepCount())
                    .sensors(config.getSensors())
                    .sendInterval(config.getSendInterval())
                    .build();

            Thread thread = new Thread(new ContainerRunnable(
                containerConfig, parserService, sendService
            ));

            thread.start();
        }
    }
}
