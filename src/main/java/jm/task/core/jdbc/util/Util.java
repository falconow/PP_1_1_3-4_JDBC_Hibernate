package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    // реализуйте настройку соеденения с БД
    private static final String hostName = "localhost";
    private static final String dbName = "pre1";
    private static final String userName = "root";
    private static final String password = "KataAcademy";
    private static SessionFactory sessionFactory = null;


    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException, ClassNotFoundException {
        // Declare the class Driver for MySQL DB
        // This is necessary with Java 5 (or older)
        // Java6 (or newer) automatically find the appropriate driver.
        // If you use Java> 5, then this line is not needed.
        Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        return DriverManager.getConnection(connectionURL, userName, password);
    }

    public static SessionFactory getEntityManagerFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver")
                    .setProperty("hibernate.connection.url","jdbc:mysql://" + hostName + ":3306/" + dbName)
                    .setProperty("hibernate.connection.username",userName)
                    .setProperty("hibernate.connection.password", password)
                    .setProperty("hibernate.show_sql", "true")
                    //.setProperty("hibernate.hbm2ddl.auto", "create")
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }
}
