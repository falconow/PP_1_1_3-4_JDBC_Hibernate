package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getEntityManagerFactory().openSession();
        session.getTransaction().begin();
        session.createSQLQuery( """
                    CREATE TABLE `users` (
                    	`id` BIGINT NOT NULL AUTO_INCREMENT,
                    	`age` TINYINT NULL DEFAULT NULL,
                    	`lastName` VARCHAR(255) NULL DEFAULT NULL,
                    	`name` VARCHAR(255) NULL DEFAULT NULL,
                    	PRIMARY KEY (`id`)
                    )                  
                    """).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getEntityManagerFactory().openSession();
        session.getTransaction().begin();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
