package com.june.start.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/FromZerotoExpert")
public class StartController {
    @GetMapping
    public String getHello() {
        return "嗨，欢迎您来到 from zero to expert.";
    }
}
