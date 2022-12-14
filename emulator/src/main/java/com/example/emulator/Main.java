package com.example.emulator;

import com.example.emulator.data.EmulatorConfig;
import com.example.emulator.service.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ParserService parser = new GsonParserService();
        SendService sendService = null;
        EmulatorConfig config = null;
        try {
            config = parser.getEmulatorConfig("emulator/config.json");
            sendService = new PostSendService(config.getRequestManagerUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }

        EmulatorService emulator = new EmulatorService(config, parser, sendService);
        emulator.start();
    }
}
