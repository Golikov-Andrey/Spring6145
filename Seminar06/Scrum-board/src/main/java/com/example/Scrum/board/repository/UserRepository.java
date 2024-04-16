package com.example.Scrum.board.repository;

import com.example.Scrum.board.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Получить пользователя по уникальному идентификатору
     *
     * @param id уникальный идентификатор
     * @return пользователь
     */
    User getById(int id);

    /**
     * Обновить пользователя по заданным параметрам
     *
     * @param firstName имя пользователя
     * @param email     почта
     * @param id        уникальный идентификатор
     */
    @Modifying
    @Transactional
    @Query("UPDATE users SET firstName = :firstName, email = :email WHERE id = :id")
    void updateUser(String firstName, String email, int id);

}
