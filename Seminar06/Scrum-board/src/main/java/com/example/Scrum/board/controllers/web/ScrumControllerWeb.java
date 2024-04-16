package com.example.Scrum.board.controllers.web;

import com.example.Scrum.board.models.Task;
import com.example.Scrum.board.models.TaskStatus;
import com.example.Scrum.board.services.TaskRepositoryService;
import com.example.Scrum.board.services.UserRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * SCRUM Board. Управлене задачами
 */
@Controller
@RequiredArgsConstructor
public class ScrumControllerWeb {
    /**
     * Инкапсулируем управление пользователями в БД
     */
    private final UserRepositoryService userRepositoryService;
    /**
     * Инкапсулируем управление задачами в БД
     */
    private final TaskRepositoryService taskRepositoryService;

    /**
     * Получить главную страницу и scrum доску
     *
     * @param model взаимодействие с шаблонизатором
     * @return главная страница html
     */
    @GetMapping("/main")
    public String getScrum(Model model) {
        model.addAttribute("users", userRepositoryService.getAll());
        return "index";
    }

    /**
     * Изменить статус на DOING у задачи
     *
     * @param model взаимодействие с шаблонизатором
     * @param id    идентиификатор задачи
     * @return главная страница - scrum доска
     */
    @GetMapping("/task-doing/{id}")
    public String turnToDoing(Model model, @PathVariable("id") int id) {
        taskRepositoryService.updateStatusById(id, TaskStatus.DOING);
        return getScrum(model);
    }

    /**
     * Изменить статус на DONE у задачи
     *
     * @param model взаимодействие с шаблонизатором
     * @param id    идентиификатор задачи
     * @return главная страница - scrum доска
     */
    @GetMapping("/task-done/{id}")
    public String turnToDone(Model model, @PathVariable("id") int id) {
        taskRepositoryService.updateStatusById(id, TaskStatus.DONE);
        return getScrum(model);
    }

    /**
     * Изменить статус на TO DO у задачи
     *
     * @param model взаимодействие с шаблонизатором
     * @param id    идентиификатор задачи
     * @return главная страница - scrum доска
     */
    @GetMapping("/task-todo/{id}")
    public String turnToToDo(Model model, @PathVariable("id") int id) {
        taskRepositoryService.updateStatusById(id, TaskStatus.TO_DO);
        return getScrum(model);
    }

    /**
     * Удалить задачу
     *
     * @param model взаимодействие с шаблонизатором
     * @param id    идентиификатор задачи
     * @return главная страница - scrum доска
     */
    @GetMapping("/task-delete/{id}")
    public String turnToDelete(Model model, @PathVariable("id") int id) {
        taskRepositoryService.delTask(id);
        return getScrum(model);
    }

    /**
     * Получить форму по редактированию задачи
     *
     * @param task задача
     * @return форма html для редактирования
     */
    @GetMapping("/edit-task/{task}")
    public String formUpdateTask(@PathVariable("task") Task task) {
        return "edit-task";
    }

    /**
     * Редактирование задачи в базе данных
     *
     * @param task принимаемая задача из редакции
     * @param id   айди задачи для редактирования
     * @return главная страница - scrum доска
     */
    @PostMapping("/edit-task/{id}")
    public String updateTask(Task task, @PathVariable("id") int id) {
        taskRepositoryService.updateTask(id, task.getTitle(), task.getDescription());
        return "redirect:/main";
    }

    /**
     * Форма создания новой задачи
     *
     * @param task  передаваемая задача
     * @param id    уникальный идентификатор пользователя
     * @param model связь с шаблонизатором
     * @return возвращаем форму html
     */
    @GetMapping("/task-create/{id}")
    public String getCreateTaskForm(Task task, @PathVariable("id") int id, Model model) {
        model.addAttribute("id", id);
        return "task-create";
    }

    /**
     * Создание новой задачи
     *
     * @param task  передаваемая задача из браузера
     * @param id    уникальный идентификатор пользователя
     * @param model связь с шаблонизатором
     * @return редирект на главную страницу
     */
    @PostMapping("/task-create/{id}")
    public String createTask(Task task, @PathVariable("id") int id, Model model) {
        model.addAttribute("id", id);
        taskRepositoryService.taskInsert(task.getTitle(), task.getDescription(), id);
        return "redirect:/main";
    }
}
