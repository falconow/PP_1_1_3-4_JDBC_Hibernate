package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        // Получаем сервис для работы User
        UserService userService = new UserServiceImpl();

        //Создаем экземпляры User
        List<User> listUsers = new ArrayList<>();
        listUsers.add(new User("Dima", "Sokolov", (byte) 29));
        listUsers.add(new User("Ivanov", "Grigoriy", (byte) 29));
        listUsers.add(new User("Anna", "Romanova", (byte) 32));
        listUsers.add(new User("Vladislav", "Alexandrov", (byte) 25));

        //Создаем таблицу в БД
        userService.createUsersTable();

        //Записываем пользователей в БД
        for (User user: listUsers) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.printf("User с именем – %s добавлен в базу данных", user.getName());
            System.out.println();
        }

        //Получаем всех пользователей
        listUsers = userService.getAllUsers();
        System.out.println(listUsers.toString());

        //Очищаем таблицу
        userService.cleanUsersTable();

        //Удаляем таблицу
        userService.dropUsersTable();
    }
}
