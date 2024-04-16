package com.example.Scrum.board.repository;

import com.example.Scrum.board.models.Task;
import com.example.Scrum.board.models.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * Собственный метод для поиска по названию задачи
     *
     * @param title назание задачи
     * @return Список задач с одинаковыми названиями
     */
    List<Task> getByTitle(String title);

    /**
     * Получить задачу по статусу выполнения
     *
     * @param taskStatus статус задачи
     * @return список задач
     */
    List<Task> getByTaskStatus(TaskStatus taskStatus);

    /**
     * Получить задачу по идентификатору задачи
     *
     * @param id уникальный идентификатор задачи
     * @return задача
     */
    Task getById(int id);

    /**
     * Кастомный запрос
     *
     * @param title       новое название задачи
     * @param description новое описание задачи
     * @param taskStatus  новый статус задачи
     * @param id          идентификатор по которому произвести обновление
     */
    @Modifying
    @Transactional
    @Query("UPDATE tasks SET title=:title, description=:description, taskStatus=:taskStatus WHERE id=:id")
    void updateTask(String title, String description, TaskStatus taskStatus, int id);

    /**
     * Кастомный запрос на создание новой задачи
     *
     * @param title       Название задачи
     * @param description опиание задачи
     * @param id          уникальный идентификатор пользователя
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tasks VALUES (DEFAULT, :title, :description, 'TO_DO', CURRENT_TIMESTAMP, :id)", nativeQuery = true)
    void insertTask(@Param("title") String title, @Param("description") String description, @Param("id") int id);

}