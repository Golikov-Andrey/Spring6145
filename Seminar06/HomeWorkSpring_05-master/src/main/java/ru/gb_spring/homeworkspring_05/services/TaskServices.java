package ru.gb_spring.homeworkspring_05.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb_spring.homeworkspring_05.model.Task;
import ru.gb_spring.homeworkspring_05.model.TaskStatus;
import ru.gb_spring.homeworkspring_05.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static ru.gb_spring.homeworkspring_05.model.TaskStatus.IN_PROGRESS;
import static ru.gb_spring.homeworkspring_05.model.TaskStatus.NOT_STARTED;
import static ru.gb_spring.homeworkspring_05.model.TaskStatus.COMPLETED;

/**
 * Сервис работы с задачами
 */
@Service
public class TaskServices {

    @Autowired
    TaskRepository taskRep;

    /** Получить список всех задач
     * @return - список задач
     */
    public List<Task> getAllTask() {
        return StreamSupport.stream(taskRep.findAll().spliterator(), false).collect(Collectors.toList());
    }

    /** Записать задачу в базу
     * @param task - задача для записи
     */
    public void saveTask(Task task) {
        taskRep.save(task);
    }

    /** Создание новой задачи по имеющемуся Наименованияю и Описанию
     * создает задачу с текущим временем и статусом "Не запущена"
     * @param description - описание задачи
     * @return - созданная задача
     */
    public Task addTask(String description) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(NOT_STARTED);
        task.setCreatedDate(LocalDateTime.now());
        return task;
    }

    /** Удаляет задачу по id
     * @param id - идентификатор удаляемой задачи
     */
    public void delTask(Long id) {
        if (taskRep.existsById(id))
            taskRep.deleteById(id);
    }

    /** Получить список задач по их статусу
     * @param taskStatus - статус задач для отбора
     * @return - список полученных задач
     */
    public List<Task> getTaskByStatus(TaskStatus taskStatus) {
        return taskRep.findByStatus(taskStatus);
    }

    /** Обновляет статус задачи с заданным id на следующий
     *  Не запущена >> Выполняется >> Выполнена
     *  в случае статуса Выполнена - ничего не изменяет
     * @param id идентификатор задачи
     */
    public void updateStatus(Long id) {
        if (taskRep.existsById(id)) {
            Task task = taskRep.findById(id).orElse(null);
            if (task != null) {
                switch (task.getStatus()) {
                    case NOT_STARTED -> {
                        task.setStatus(IN_PROGRESS);
                    }
                    case IN_PROGRESS -> {
                        task.setStatus(COMPLETED);
                    }
                    default -> {
                        return;
                    }
                }
            }
            taskRep.save(task);
        }
    }
}
