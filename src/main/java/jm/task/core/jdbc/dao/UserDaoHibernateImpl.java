package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS User (id int NOT NULL AUTO_INCREMENT" +
            ",name varchar(255),lastName varchar(255),age int,  PRIMARY KEY (id));";
    private static final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS User;";
    private static final String REMOVE_USER = "delete User where id = :id";
    private static final String GET_ALL_USERS = "from User";
    private static final String CLEAN_USERS_TABLE = "TRUNCATE TABLE User;";
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        try (Session session = Util.getHibernateSession().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(CREATE_USERS_TABLE)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {

        try (Session session = Util.getHibernateSession().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(DROP_USERS_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        User user = new User(name, lastName, age);

        try (Session session = Util.getHibernateSession().openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {

        try (Session session = Util.getHibernateSession().openSession()) {
            session.beginTransaction();
            session.createQuery(REMOVE_USER)
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try (Session session = Util.getHibernateSession().openSession()) {
            session.beginTransaction();
            users = session.createQuery(GET_ALL_USERS).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = Util.getHibernateSession().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(CLEAN_USERS_TABLE)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }
}