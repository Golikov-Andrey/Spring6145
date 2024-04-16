package com.example.Scrum.board.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Сущность - таблица в Базе данных
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @Column(name = "date_create")
    private LocalDateTime date = LocalDateTime.now();
    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    /**
     * Метод конвертации статуса задачи в string для шаблонизатора
     *
     * @return строковое значение статуса
     */
    public String taskToString() {
        return this.taskStatus.toString();
    }

    /**
     * Связь многие к одному для таблицы с пользователями
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
