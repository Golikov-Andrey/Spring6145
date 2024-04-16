package com.example.Scrum.board.controllers.rest;


import com.example.Scrum.board.models.User;
import com.example.Scrum.board.services.UserRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST запросы для юзеров
 */
@RestController
@RequiredArgsConstructor
public class UsersController {
    /**
     * Управление пользователями
     */
    private final UserRepositoryService userService;

    /**
     * Показать всех пользователей
     *
     * @return статус выполнения
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    /**
     * Создание нового пользователя
     *
     * @param user пользователь из body rest запроса
     * @return статус выполнения
     */
    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    /**
     * Обновить пользователя
     *
     * @param user пользователь из body rest запроса
     * @return статус выполнения
     */
    @PostMapping("/update-user")
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok().build();
    }

    /**
     * Удалить пользователя
     *
     * @param user пользователь из body rest запроса
     * @return статус выполнения
     */
    @PostMapping("/delete-user")
    public ResponseEntity<Void> deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return ResponseEntity.ok().build();
    }
}
