package com.example.Scrum.board.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    private String email;
    /**
     * Связываем одного пользователя ко многим задачам
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL )
    private List<Task> task;
}
