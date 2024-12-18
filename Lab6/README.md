# Программа для работы с базой данных в JavaFX

![Java](https://img.shields.io/badge/Java-17-orange) ![JavaFX](https://img.shields.io/badge/JavaFX-23.0.1-blue) ![MySQL](https://img.shields.io/badge/MySQL-8.0-lightblue)

## Описание

Это приложение на **JavaFX** позволяет управлять данными из базы MySQL. Включает функции: загрузка, добавление, редактирование, сохранение и сортировка записей через удобный графический интерфейс.

---

## Настройка базы данных

1. Создайте базу данных **SortingDB** и таблицу **people**:

    ```sql
    CREATE DATABASE SortingDB;

    USE SortingDB;

    CREATE TABLE people (
        Id INT AUTO_INCREMENT PRIMARY KEY,
        Name VARCHAR(100),
        Age INT,
        sort_order INT
    );
    ```

2. Укажите параметры подключения к базе в классе `DataBaseConnection`:

    ```java
    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SortingDB", "root", "");
    ```

---

## Функционал

### Доступные кнопки:
- **Load** — Загрузка данных из базы в таблицу.
- **Save** — Сохранение изменений в базу.
- **Sort** — Сортировка записей по имени.
- **Добавить** — Добавление нового человека (например, "Новый Человек", 25 лет).

---

## Проблемы и их решения

1. **Подключение к базе данных**
   - Проблемы с доступом к MySQL были решены настройкой корректных учетных данных и добавлением драйвера MySQL Connector/J.

2. **Редактирование таблицы**
   - Использованы `TextFieldTableCell` и обработчики событий для изменения данных в таблице прямо из интерфейса.

3. **SQL-операции**
   - Реализован метод `saveAll`, обрабатывающий добавление новых записей и обновление существующих.

---

## Требования

- **Java 17** или выше.
- **JavaFX SDK 23.0.1**.
- **MySQL Server** и драйвер MySQL Connector/J.

---

## Установка

1. Склонируйте репозиторий и перейдите в Lab6:
    ```bash
    git clone https://github.com/Corovinus/Industrial-Programming
    ```

2. Убедитесь, что установлены все зависимости:
   - JavaFX
   - MySQL Connector/J

3. Запустите приложение через любую IDE с поддержкой JavaFX, например, **IntelliJ IDEA**.

---

## Авторы

- **Corovinus**  
  Контакты: apancenok@gmail.com (https://github.com/Corovinus)
