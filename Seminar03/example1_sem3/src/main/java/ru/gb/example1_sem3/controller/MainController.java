package ru.gb.example1_sem3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/home")
    public String getHome()
    {
        return "home.html";
    }

    @RequestMapping("/cats")
    public String getCats()
    {
        return "cats.html";
    }

    @RequestMapping("/game")
    public String getGame()
    {
        return "game.html";
    }
}
