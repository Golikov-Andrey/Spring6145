package ru.geekbrain.empl6sem8interceptors.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

}