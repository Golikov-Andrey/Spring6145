package org.belotelov.hw4.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.belotelov.hw4.model.Person;
import org.belotelov.hw4.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class PersonController {
    private final PersonService personService;

    @GetMapping()
    public String showAllPersons(Model model) {
        List<Person> persons = personService.findAllPersons();
        model.addAttribute("persons", persons);
        return "index";
    }

    @GetMapping("/new")
    public String showNewPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }

        personService.addPerson(person);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        Person person = personService.getOne(id);
        model.addAttribute("person", person);
        return "update";
    }

    @PostMapping("/update")
    public String updatePerson(@Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update";
        }
        personService.updatePerson(person);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personService.deletePersonById(id);
        return "redirect:/";
    }
}
