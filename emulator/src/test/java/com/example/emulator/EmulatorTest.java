package com.example.emulator;

import com.example.emulator.data.ContainerConfig;
import com.example.emulator.data.SensorConfig;
import com.example.emulator.runnable.ContainerRunnable;
import com.example.emulator.service.GsonParserService;
import com.example.emulator.service.SendService;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class EmulatorTest {
    private final SendService mockSendService = System.out::println;

    @Test
    public void singleContainerTest() throws ParseException {
        Calendar time = new GregorianCalendar();
        time.setTime(new SimpleDateFormat("dd.MM.yyyy HH:mm").parse("19.10.2022 12:20"));

        List<SensorConfig> sensors = new ArrayList<>();
        sensors.add(new SensorConfig(
                1, "1", 45.3, 42
        ));

        sensors.add(new SensorConfig(
                2, "2", 5.3, .7
        ));

        sensors.add(new SensorConfig(
                3, "3", 100, 75
        ));

        ContainerConfig config = ContainerConfig.builder()
                .containerNumber(3)
                .startEmulatingTime(time)
                .maxTimeShift(1)
                .recordingStep(1)
                .stepCount(20)
                .sendInterval(1)
                .sensors(sensors)
                .build();

        ContainerRunnable runnable = new ContainerRunnable(config, new GsonParserService(), mockSendService);

        runnable.run();
    }
}
