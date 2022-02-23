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
            System.out.println("Error create table Users");
            e.printStackTrace();
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
            System.out.println("Error drop table Users");
            e.printStackTrace();
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
            throw new RuntimeException("Error save user", e);
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
            throw new RuntimeException("Error remove user",e);
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
                User user = new User(rs.getString("name"), rs.getString("lastName"), rs.getByte("age"));
                user.setId(rs.getLong("id"));
                result.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error get all users",e);
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
          throw new RuntimeException("Error delete table Users", e);
        } finally {
            closeConnection();
        }
    }

    private void getConnection() {
        try {
            connection = Util.getMySQLConnection();
        } catch (SQLException e) {
           throw new RuntimeException("Error connection to database", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class Connection not found", e);
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error close connection");
            e.printStackTrace();
        }
    }
}
