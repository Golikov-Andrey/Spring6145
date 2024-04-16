package ru.gb_spring.homeworkspring_05.model;

/**
 * Статусы задачи
 *
 * @NOT_STARTED - Не запущена
 * @IN_PROGRESS - Выполняется (в работе)
 * @COMPLETED - Выполнена (завершена)
 *
 */
public enum TaskStatus {
    NOT_STARTED{
        @Override
        public String toString() {
            return "Не выполняется";
        }
    },
    IN_PROGRESS{
        @Override
        public String toString() {
            return "Выполняется";
        }
    },
    COMPLETED{
        @Override
        public String toString() {
            return "Завершена";
        }
    }
}
