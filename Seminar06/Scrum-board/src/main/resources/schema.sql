CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name varchar(50) NOT NULL,
    email varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title varchar(50) NOT NULL,
    description varchar(50) NOT NULL,
    task_status varchar(50) NOT NULL,
    date_create TIMESTAMP WITH TIME ZONE NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

--task_status varchar(50) NOT NULL DEFAULT 'TO_DO',

INSERT INTO users VALUES (DEFAULT, 'Roman', 'sup.makulin@mail.ru');
INSERT INTO users VALUES (DEFAULT, 'Olga', 'sadasdad@mail.ru');
INSERT INTO users VALUES (DEFAULT, 'Ann', '123gfg@mail.ru');

INSERT INTO tasks VALUES (DEFAULT, 'Молоко', 'Купить 2 булытки', 'TO_DO', CURRENT_TIMESTAMP, 1);
INSERT INTO tasks VALUES (DEFAULT, 'ДЗ', 'Сделать школьное', 'TO_DO', CURRENT_TIMESTAMP, 1);
INSERT INTO tasks VALUES (DEFAULT, 'Кофе', 'Сделать кофе', 'DOING', CURRENT_TIMESTAMP, 1);
INSERT INTO tasks VALUES (DEFAULT, 'Машина', 'Обновить ТО', 'DONE', CURRENT_TIMESTAMP, 1);
INSERT INTO tasks VALUES (DEFAULT, 'Кот', 'Погладлить кота', 'TO_DO', CURRENT_TIMESTAMP, 2);