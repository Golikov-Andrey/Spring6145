package ru.gb_spring.homeworkspring_05.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Сущность "Задача"
 * @id - идентификатор
 * @description - описание
 * @status - статус
 * @createdDate - дата создания
 */
@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "STATUS")
    private TaskStatus status;
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    public String getStatusWeb() {
        return status.toString();
    }
}
