package com.example.webapi.controller;

import com.example.webapi.aop.AuthRequired;
import com.example.webapi.dto.PageDto;
import com.example.webapi.exception.InvalidAttributeException;
import com.example.webapi.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WebController {

    private final ContainerService containerService;

    @Autowired
    public WebController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @GetMapping("/api/v1/data")
    @AuthRequired
    public List<PageDto> get(
            @RequestParam(value = "time_after") Integer timeAfter,
            @RequestParam(value = "time_to") Integer timeTo,
            @RequestParam(value = "string_for_page") Integer stringForPage,
            HttpServletRequest request) {

        if (timeAfter >= timeTo) {
            throw new InvalidAttributeException("time_after must be < time_to");
        }

        if (stringForPage <= 0) {
            throw new InvalidAttributeException("string_for_page must be > 0");
        }

        Instant start = containerService.getStartTime().toInstant();
        start = start.plus((long)(timeAfter), ChronoUnit.SECONDS);
        Instant end = start.plus((long)(timeTo), ChronoUnit.SECONDS);

        PageRequest pageRequest = PageRequest.of(0, stringForPage, Sort.by("timestamp"));

        return containerService.generatePages(pageRequest, Timestamp.from(start), Timestamp.from(end));
    }
}
