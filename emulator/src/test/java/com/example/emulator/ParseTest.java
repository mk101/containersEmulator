package com.example.emulator;

import com.example.emulator.data.EmulatorConfig;
import com.example.emulator.data.SensorConfig;
import com.example.emulator.service.GsonParseService;
import com.example.emulator.service.ParseService;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ParseTest {

    @Test
    public void parseJson() {
        ParseService parseService = new GsonParseService();
        EmulatorConfig emulatorConfig = null;
        try {
            emulatorConfig = parseService.getEmulatorConfig("config_test.json");
        } catch (IOException e) {
            Assert.fail();
        }

        Assert.assertEquals(emulatorConfig.getContainersCount(), 100);
        try {
            Assert.assertEquals(emulatorConfig.getStartEmulatingTime(), new SimpleDateFormat("dd.MM.yyyy HH:mm").parse("19.10.2022 12:20"));
        } catch (ParseException e) {
            Assert.fail();
        }
        Assert.assertEquals(emulatorConfig.getMaxTimeShift(), 52.0, 0.000001d);
        Assert.assertEquals(emulatorConfig.getRecordingStep(), 3.0, 0.000001d);
        Assert.assertEquals(emulatorConfig.getStepCount(), 4242);

        Assert.assertEquals(emulatorConfig.getSensors().size(), 2);

        SensorConfig sensor1 = emulatorConfig.getSensors().get(0);
        Assert.assertEquals(sensor1.getId(), 1);
        Assert.assertEquals(sensor1.getName(), "Sensor 1");
        Assert.assertEquals(sensor1.getAverage(), 14.6, 0.000001d);
        Assert.assertEquals(sensor1.getMaximumDispersion(), 10, 0.000001d);

        SensorConfig sensor2 = emulatorConfig.getSensors().get(1);
        Assert.assertEquals(sensor2.getId(), 2);
        Assert.assertEquals(sensor2.getName(), "Sensor 2");
        Assert.assertEquals(sensor2.getAverage(), 10, 0.000001d);
        Assert.assertEquals(sensor2.getMaximumDispersion(), 1, 0.000001d);

        Assert.assertEquals(emulatorConfig.getSendInterval(), 10);
    }
}
