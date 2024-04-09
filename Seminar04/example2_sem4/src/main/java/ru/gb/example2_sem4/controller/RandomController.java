package ru.gb.example2_sem4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class RandomController {
    @GetMapping("/random")
    public String getRandom(Model model) {
        Random rnd = new Random();
        int num = rnd.nextInt(100);
        model.addAttribute("number", num);
        return "random";
    }

    @GetMapping("/random/{min}/{max}")
    public String getMinMaxRandom(Model model, @PathVariable(name = "min") int min, @PathVariable(name = "max") int max) {
        Random rnd = new Random();
        int num = rnd.nextInt(min, max);
        model.addAttribute("number",num);
        model.addAttribute("min",min);
        model.addAttribute("max",max);
        return "minMaxRandom";
    }

    @GetMapping("/randomPath")
    public String getPathRandom(Model model, @RequestParam(name = "min") int min, @RequestParam(name = "max") int max) {
        Random rnd = new Random();
        int num = rnd.nextInt(min, max);
        model.addAttribute("number",num);
        model.addAttribute("min",min);
        model.addAttribute("max",max);
        return "minMaxRandom";
    }


}
