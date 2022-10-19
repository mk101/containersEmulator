package com.example.emulator.service;

import com.example.emulator.data.EmulatorConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GsonParseService implements ParseService {
    private final Gson gson;

    public GsonParseService() {
        gson = new GsonBuilder().setDateFormat("dd.MM.yyyy HH:mm").create();
    }

    @Override
    public EmulatorConfig getEmulatorConfig(String pathToConfig) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(pathToConfig), StandardCharsets.UTF_8);
        String json = String.join(" ", lines);

        return gson.fromJson(json, EmulatorConfig.class);
    }
}
