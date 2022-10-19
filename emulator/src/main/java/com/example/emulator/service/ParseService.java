package com.example.emulator.service;

import com.example.emulator.data.EmulatorConfig;

import java.io.IOException;

public interface ParseService {
    EmulatorConfig getEmulatorConfig(String pathToConfig) throws IOException;
}
