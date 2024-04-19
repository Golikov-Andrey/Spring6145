
# Базовое задание:

**Внимание** ДЗ выполнять в версии SpringBoot:**3.2.5**(на основе example4_sem7)

## Вам необходимо создать Spring Boot приложение

Приложение, которое управляет доступом к ресурсам в зависимости от роли пользователя. У вас должно быть два типа пользователей: USER и ADMIN. 

1. Создайте ресурс /private-data, доступный только для аутентифицированных пользователей с ролью ADMIN
2. Создайте ресурс /public-data, доступный для всех аутентифицированных пользователей независимо от их роли
3. Реализуйте форму входа для аутентификации пользователей с использованием стандартных средств Spring Security
4. Если неаутентифицированный пользователь пытается получить доступ к /private-data, он должен быть перенаправлен на форму входа


Подсказка:

Файл HTML:
``` 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="/login" method="post">
    <div>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"/>
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"/>
    </div>
    <input type="submit" value="Login"/>
</form>
</body>
</html>
```

# Задание со звездочкой (при выполнении этого задания, выполнять базовое не нужно):

Разработать 3 проекта реализации составных частей технологии авторизации OAuth 2.0 на SpringBoot:

1. Сервер ресурсов
2. Сервер авторизации на базе JWT
3. Клиент

Клиент должен при нажатии на кнопку (простая форма) получить доступ к ресурсу на сервере (к примеру - **картинка котика**)

Логин: **user**, пароль: **password**

Подсказки:

- для сервера аутентификации

```
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>
```

- для сервера ресурсов

```
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-oauth2-authorization-server</artifactId>
</dependency>
```

- для клиента системы

```
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-oauth2-client</artifactId>
</dependency>
```



