package jm.task.core.jdbc;

/*
Описание задачи:

Необходимо ознакомиться с заготовкой и доработать приложение, которое взаимодействует с базой оперируя пользователем ( класс User ) и проверить свои методы заранее написанными JUnit тестами. По итогу все тесты должны быть пройдены. Разрешается посмотреть реализацию тестов.

Для запуска теста необходимо найти класс в папке test ( показано в предыдущей лекции ) и при нажатии на него правой кнопкой мыши запустить, выбрав Run "Имя класса"

Класс UserHibernateDaoImpl в рамках этой задачи не затрагивается (остаётся пустой)

User представляет из себя сущность с полями:

Long id
String name
String lastName
Byte age
Архитектура приложения создана с опорой на паттерн проектирования MVC (частично, у нас не WEB приложение)

Ознакомиться с паттерном можно здесь

Требования к классам приложения:

 Классы dao/service должны реализовывать соответствующие интерфейсы
 Класс dao должен иметь конструктор пустой/по умолчанию
 Все поля должны быть private
 service переиспользует методы dao
 Обработка всех исключений, связанных с работой с базой данных должна находиться в dao
 Класс Util должен содержать логику настройки соединения с базой данных
Необходимые операции:

 Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует
 Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
 Очистка содержания таблицы
 Добавление User в таблицу
 Удаление User из таблицы ( по id )
 Получение всех User(ов) из таблицы
Алгоритм работы приложения:

         В методе main класса Main должны происходить следующие операции:

 Создание таблицы User(ов)
 Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
 Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
 Очистка таблицы User(ов)
 Удаление таблицы
 */

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Богдан", "Башков" , (byte) 19);
        userService.saveUser("Иван", "Рогозин" , (byte) 43);
        userService.saveUser("Диляра", "Рахманбекова" , (byte) 19);
        userService.saveUser("Кирилл", "Заброда" , (byte) 21);

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
