package com.example.emulator;

import com.example.emulator.data.EmulatorConfig;
import com.example.emulator.service.EmulatorService;
import com.example.emulator.service.GsonParserService;
import com.example.emulator.service.ParserService;
import com.example.emulator.service.SendService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ParserService parser = new GsonParserService();
        SendService sendService = System.out::println;
        EmulatorConfig config = null;
        try {
            config = parser.getEmulatorConfig("emulator/config.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        EmulatorService emulator = new EmulatorService(config, parser, sendService);
        emulator.start();
    }
}
