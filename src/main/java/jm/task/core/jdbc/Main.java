package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao test = new UserDaoJDBCImpl();
        //test.dropUsersTable();
//        test.createUsersTable();
//        test.saveUser("Dima", "Sokolov", (byte) 29);
//        test.saveUser("Luda", "Sokolova", (byte) 29);
       // test.removeUserById(1);
        List<User> listUsers = test.getAllUsers();
        System.out.println(listUsers.toString());


    }
}
