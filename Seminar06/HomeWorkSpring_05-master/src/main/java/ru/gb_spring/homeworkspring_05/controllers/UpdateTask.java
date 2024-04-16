package ru.gb_spring.homeworkspring_05.controllers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.gb_spring.homeworkspring_05.services.TaskServices;

/**
 * Обработчик изменения статуса задачи
 */
@Controller
@Log
public class UpdateTask {

    @Autowired
    TaskServices ts;

    /**
     * Обработка GET запроса за изменение статуса
     *
     * @param id - идентификатор задачи
     * @return - редирект на стартовую страницу
     */
    @GetMapping("/upd/{id}")
    public String updateTaskStatus(@PathVariable Long id) {
        ts.updateStatus(id);
        return "redirect:/";
    }
}
