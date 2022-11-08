package com.example.webapi.controller;

import com.example.webapi.aop.AuthRequired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WebController {

    @GetMapping("/api/v1/data")
    @AuthRequired
    public String get(
            @RequestParam(value = "time_after") Integer timeAfter,
            @RequestParam(value = "time_to") Integer timeTo,
            @RequestParam(value = "string_for_page") Integer stringForPage,
            HttpServletRequest request) {
        return "Hello world";
    }
}
