package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = """
                    CREATE TABLE `users` (
                    	`id` INT(10) NOT NULL AUTO_INCREMENT,
                    	`name` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'utf8_general_ci',
                    	`lastName` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'utf8_general_ci',
                    	`age` TINYINT(3) NOT NULL DEFAULT '0',
                    	PRIMARY KEY (`id`) USING BTREE
                    )
                    """;
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error create table Users");
        } finally {
            closeConnection();
        }
    }

    public void dropUsersTable() {
        getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "DROP TABLE `Users`;";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error drop table Users");
        } finally {
            closeConnection();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        getConnection();
        String sql = "INSERT INTO `Users` (`name`, `lastName`, `age`) VALUES (?, ?, ?)";
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, name);
            prepareStatement.setString(2, lastName);
            prepareStatement.setByte(3, age);
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error insert user");
        } finally {
            closeConnection();
        }
    }

    public void removeUserById(long id) {
        getConnection();
        String sql = "DELETE FROM `Users` WHERE `id` = ?";
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setLong(1, id);
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error remove user");
        } finally {
            closeConnection();
        }
    }

    public List<User> getAllUsers() {
        getConnection();
        String sql = "SELECT * FROM `Users`";
        List<User> result = new ArrayList<>();
        try {
            Statement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.add(new User(rs.getString("name"), rs.getString("lastName"), rs.getByte("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error select all users");
        } finally {
            closeConnection();
        }
        return result;
    }

    public void cleanUsersTable() {
        getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM `Users`;";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error delete table Users");
        } finally {
            closeConnection();
        }
    }

    private void getConnection() {
        try {
            connection = Util.getMySQLConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connection to database");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class Connection not found");
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error close connection");
        }
    }
}
