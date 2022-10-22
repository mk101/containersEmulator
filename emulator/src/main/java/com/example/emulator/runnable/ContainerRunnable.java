package com.example.emulator.runnable;

import com.example.emulator.data.ContainerConfig;
import com.example.emulator.dto.ContainerDto;
import com.example.emulator.service.ParserService;
import com.example.emulator.service.SendService;
import lombok.SneakyThrows;

import java.util.*;

public class ContainerRunnable implements Runnable {
    private final ContainerConfig config;
    private final Random random;
    private final ParserService parserService;
    private final SendService sendService;

    public ContainerRunnable(ContainerConfig config, ParserService parserService, SendService sendService) {
        this.config = config;
        this.parserService = parserService;
        this.sendService = sendService;
        random = new Random();
    }

    @SneakyThrows
    @Override
    public void run() {
        Calendar time = (Calendar) config.getStartEmulatingTime().clone();

        for (int i = 0; i < config.getStepCount(); i++) {
            int offset = random.nextInt((int) (config.getMaxTimeShift() * 1000));
            time.add(Calendar.MILLISECOND, offset);
            Thread.sleep(offset);

            ContainerDto dto = ContainerDto
                    .builder()
                    .containerNumber(config.getContainerNumber())
                    .timeStamp(time.getTime())
                    .sensorsValues(new ArrayList<>())
                    .build();

            for (var sensor : config.getSensors()) {
                double value = sensor.getAverage() - sensor.getMaximumDispersion() + (2 * sensor.getMaximumDispersion()) * random.nextDouble();
                dto.getSensorsValues().add(value);
            }

            String response = parserService.ContainerToString(dto);

            sendService.send(response);

            time.add(Calendar.SECOND, config.getSendInterval());
            Thread.sleep(config.getSendInterval() * 1000L);

            time.add(Calendar.MILLISECOND, (int) (config.getRecordingStep() * 1000L));
            Thread.sleep((long) (config.getRecordingStep() * 1000L));
        }
    }
}
