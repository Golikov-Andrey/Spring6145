package com.example.Scrum.board.services;

import com.example.Scrum.board.models.User;
import com.example.Scrum.board.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserRepositoryService {
    /**
     * Управление пользователями в БД
     */
    private final UserRepository userRepository;

    /**
     * Создание пользователя
     *
     * @param user новый пользователь
     * @return пользователь
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Получить всех пользователей
     *
     * @return список пользователей
     */
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Получить пользователя по ID
     *
     * @param id уникальный идентификатор пользователя
     * @return пользователь
     */
    public User getById(int id) {
        return userRepository.getById(id);
    }

    /**
     * Обновить пользователя
     *
     * @param user пользователь
     */
    public void update(User user) {
        userRepository.updateUser(user.getFirstName(), user.getEmail(), user.getId());
    }

    /**
     * Удалить пользователя
     *
     * @param user пользователь
     */
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
