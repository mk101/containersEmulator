package com.example.emulator.service;

import com.example.emulator.data.EmulatorConfig;
import lombok.Getter;

public class EmulatorService {
    @Getter
    private final EmulatorConfig config;

    public EmulatorService(EmulatorConfig config) {
        this.config = config;
    }

    public void start() {

    }
}
