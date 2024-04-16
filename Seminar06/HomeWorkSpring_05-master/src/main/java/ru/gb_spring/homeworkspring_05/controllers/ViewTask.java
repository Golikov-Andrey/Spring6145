package ru.gb_spring.homeworkspring_05.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb_spring.homeworkspring_05.model.Task;
import ru.gb_spring.homeworkspring_05.model.TaskStatus;
import ru.gb_spring.homeworkspring_05.services.TaskServices;

import java.util.List;

/**
 * Обработка стартовой страницы
 */
@Controller
public class ViewTask {
    @Autowired
    TaskServices ts;

    /**
     * Обработчик GET запроса стартовой страницы с фильтрацией
     *
     * @param filter - (необязательный параметр) установленный фильтр (null,"all","nostart","inwork","ready")
     *               null - не установленный параметр приравнивается к параметру "all" - вывод без фильтрации,
     *               "all" - вывод всех задач,
     *               "nostart" - вывод только не запущенных задач,
     *               "inwork" - вывод задач в работе,
     *               "ready" - вывод завершенных задач.
     * @return - стартовая страница с учетом параметра фильтра
     */
    @GetMapping("/")
    public String viewTaskGet(@RequestParam(value = "filter", required = false) String filter, Model model) {
        if (filter == null) filter = "all";
        List<Task> tasks = switch (filter) {
            case "nostart" -> ts.getTaskByStatus(TaskStatus.NOT_STARTED);
            case "inwork" -> ts.getTaskByStatus(TaskStatus.IN_PROGRESS);
            case "ready" -> ts.getTaskByStatus(TaskStatus.COMPLETED);
            default -> ts.getAllTask();
        };
        model.addAttribute("tasks", tasks);
        model.addAttribute("filter", filter);
        return "index";
    }
}

