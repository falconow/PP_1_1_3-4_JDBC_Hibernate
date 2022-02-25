package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.cfgxml.spi.LoadedConfig;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.mapping.Property;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.PersistenceUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String hostName = "localhost";
    private static String dbName = "pre1";
    private static String userName = "root";
    private static String password = "KataAcademy";
    private static SessionFactory sessionFactory = null;


    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {

        String hostName = "localhost";
        String dbName = "pre1";
        String userName = "root";
        String password = "KataAcademy";

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
//            LoadedConfig loadedConfig = new LoadedConfig("jm.task.core.jdbc");
//            Map configurationValues = loadedConfig.getConfigurationValues();
//            configurationValues.put("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
//            configurationValues.put("hibernate.connection.url","jdbc:mysql://" + hostName + ":3306/" + dbName);
//            configurationValues.put("hibernate.connection.username",userName);
//            configurationValues.put("hibernate.connection.password", password);
//            configurationValues.put("hibernate.hbm2ddl.auto", "create");
//
//            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
//                    .configure(loadedConfig)
//                    .build();
//
//            Metadata metadata = new MetadataSources( standardRegistry )
//                    .addAnnotatedClass( User.class )
//                    .getMetadataBuilder()
//                    .applyImplicitNamingStrategy( ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
//                    .build();
//
//            SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();
//
//            sessionFactory = sessionFactoryBuilder.build();

            sessionFactory = new Configuration()
                    .setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver")
                    .setProperty("hibernate.connection.url","jdbc:mysql://" + hostName + ":3306/" + dbName)
                    .setProperty("hibernate.connection.username",userName)
                    .setProperty("hibernate.connection.password", password)
                    .setProperty("hibernate.show_sql", "true")
                    //.setProperty("hibernate.hbm2ddl.auto", "create")
                    .addAnnotatedClass(jm.task.core.jdbc.model.User.class)
                    .buildSessionFactory();
      }

        return sessionFactory;

    }
}
