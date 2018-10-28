DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM meals;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, description, datetime, calories)
VALUES
       (100000, 'Завтрак', '2018-10-22 07:00:00', 500),
       (100000, 'Обед', '2018-10-22 14:00:00', 1000),
       (100000, 'Ужин', '2018-10-22 19:00:00', 500),
       (100000, 'Завтрак', '2018-10-23 07:00:00', 750),
       (100000, 'Обед', '2018-10-23 14:00:00', 1000),
       (100000, 'Ужин', '2018-10-23 19:00:00', 750),
       (100001, 'Завтрак', '2018-10-22 07:00:00', 600),
       (100001, 'Обед', '2018-10-22 14:00:00', 1000),
       (100001, 'Ужин', '2018-10-22 19:00:00', 600),
       (100001, 'Завтрак', '2018-10-23 07:00:00', 450),
       (100001, 'Обед', '2018-10-23 14:00:00', 700),
       (100001, 'Ужин', '2018-10-23 19:00:00', 750);


