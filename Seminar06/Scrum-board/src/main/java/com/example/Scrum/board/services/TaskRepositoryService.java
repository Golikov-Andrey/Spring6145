package com.example.Scrum.board.services;

import com.example.Scrum.board.models.Task;
import com.example.Scrum.board.models.TaskStatus;
import com.example.Scrum.board.models.User;
import com.example.Scrum.board.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskRepositoryService {
    /**
     * Управление задачами через базу данных
     */
    private final TaskRepository taskRepository;

    /**
     * Получение списка всех задач
     *
     * @return список задач
     */
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    /**
     * Создание новой задачи
     *
     * @param task задача
     * @param user пользователь кому дать задачу
     */
    public void create(Task task, User user) {
        task.setUser(user);
        taskRepository.save(task);
    }

    public void taskInsert(String title, String description, int id) {
        taskRepository.insertTask(title, description, id);
    }

    /**
     * Получить список задач по названию
     *
     * @param title название задачи
     * @return список задач
     */
    public List<Task> getByTitle(String title) {
        return taskRepository.getByTitle(title);
    }

    /**
     * Получить список задач по статусу задачи
     *
     * @param taskStatus статус задачи
     * @return список задач
     */
    public List<Task> getByStatus(TaskStatus taskStatus) {
        return taskRepository.getByTaskStatus(taskStatus);
    }

    /**
     * Удалить задачу
     *
     * @param task задача
     */
    public void delTask(Task task) {
        taskRepository.delete(task);
    }

    /**
     * Удалить задачу по ID
     *
     * @param id идентефикатор задачи
     */
    public void delTask(int id) {
        taskRepository.delete(getById(id));
    }

    /**
     * Получить задачу по ID
     *
     * @param id уникальный идентификатор
     * @return задача
     */
    public Task getById(int id) {
        return taskRepository.getById(id);
    }

    /**
     * Обновить задачу
     *
     * @param task задача
     */
    public void updateTask(Task task) {
        taskRepository.updateTask(task.getTitle(), task.getDescription(), task.getTaskStatus(), task.getId());
    }

    /**
     * Обновить задачу по ограниченным параметрам
     *
     * @param id    идентефикатор задачи для обновления
     * @param title новое название
     * @param desc  новое описание
     */
    public void updateTask(int id, String title, String desc) {
        Task task = taskRepository.getById(id);
        if (task != null) {
            task.setTitle(title);
            task.setDescription(desc);
            taskRepository.save(task);
        } else {
            throw new RuntimeException("error");
        }
    }

    /**
     * Обновить статус задачи по ID
     *
     * @param id         уникальный идентификатор задачи
     * @param taskStatus новый статус задачи
     */
    public void updateStatusById(int id, TaskStatus taskStatus) {
        Task task = getById(id);
        task.setTaskStatus(taskStatus);
        updateTask(task);
    }

}
