package ru.gb.example1_sem4.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping("/about")
    public String getAbout() {
        return "about.html";
    }
}
