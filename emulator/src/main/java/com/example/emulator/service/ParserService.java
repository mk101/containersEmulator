package com.example.emulator.service;

import com.example.emulator.data.EmulatorConfig;
import com.example.emulator.dto.ContainerDto;

import java.io.IOException;

public interface ParserService {
    EmulatorConfig getEmulatorConfig(String pathToConfig) throws IOException;

    String ContainerToString(ContainerDto container);
}
