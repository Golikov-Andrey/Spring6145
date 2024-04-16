package ru.gb_spring.homeworkspring_05.controllers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.gb_spring.homeworkspring_05.services.TaskServices;

/**
 * Обработка запросов на удаление задачи
 * GET - "/deltask/{id}"
 * id - идентификатор задачи
 */
@Controller
@Log
public class DeleteTask {
    @Autowired
    TaskServices ts;

    /**
     * Обработчик GET запроса на удаление задачи
     *
     * @param id - идентификатор задачи
     * @return - редирект на "/"
     */
    @GetMapping("/deltask/{id}")
    public ModelAndView delTask(@PathVariable Long id) {
        ts.delTask(id);
        return new ModelAndView("redirect:/");
    }
}
