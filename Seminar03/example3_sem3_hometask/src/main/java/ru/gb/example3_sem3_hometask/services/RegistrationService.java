package ru.gb.example3_sem3_hometask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private DataProcessingService dataProcessingService;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    public DataProcessingService getDataProcessingService() {
        return dataProcessingService;
    }

    public void processRegistration(String username, int age, String email) {

        User user = new User(username, age, email);

        userService.addUser(user);

        notificationService.notify("User registered: " + user.getUsername());

        System.out.println("Процесс регистрации завершен!");
    }
}
