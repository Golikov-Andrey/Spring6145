
# Базовое задание:

Условие:

Вам предстоит создать приложение для управления списком задач с использованием Spring Boot и Spring Data JPA. Требуется реализовать следующие функции:

## 1. Добавление задачи. 

Подсказка метод в контроллере: @PostMapping public Task addTask(@RequestBody Task task)

## 2. Просмотр всех задач. 

Подсказка метод в контроллере: @GetMapping public List<Task> getAllTasks()

## 3. Просмотр задач по статусу (например, "завершена", "в процессе", "не начата"). 

Подсказка метод в контроллере: @GetMapping("/status/{status}") public List<Task> getTasksByStatus(@PathVariable TaskStatus status)

## 4. Изменение статуса задачи. 

Подсказка метод в контроллере: @PutMapping("/{id}") public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task)

## 5. Удаление задачи.

Подсказка метод в контроллере:

@DeleteMapping("/{id}")
public void deleteTask(@PathVariable Long id)

Репозитроий подсказка public interface TaskRepository extends JpaRepository<Task, Long>

## Структура задачи(класс Task):

- ID (автоинкрементное)(тип Long)
- Описание (не может быть пустым)(тип String)
- Статус (одно из значений: "не начата", "в процессе", "завершена")(Тип TaskStatus )
- Дата создания (автоматически устанавливается при создании задачи)(Тип LocalDateTime)

Подсказка понадобится энумератор:

enum TaskStatus {
NOT_STARTED, IN_PROGRESS, COMPLETED;
}

# Задание со звездочкой:

Cоздать серверное приложение, которое может получать файлы, загружаемые по протоколу HTTP из нескольких частей.

**Для основы проекта взять пример:** https://spring.io/guides/gs/uploading-files




