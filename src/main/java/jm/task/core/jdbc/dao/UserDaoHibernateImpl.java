package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS User (id int NOT NULL AUTO_INCREMENT" +
            ",name varchar(255),lastName varchar(255),age int,  PRIMARY KEY (id));";
    private static final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS User;";
    private static final String GET_ALL_USERS = "from User";
    private static final String CLEAN_USERS_TABLE = "TRUNCATE TABLE User;";
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getHibernateSession().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(CREATE_USERS_TABLE)
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.printf("%s - ошибка создания таблицы user_data", e.getMessage());


            }
        }

    @Override
    public void dropUsersTable() {

        Transaction transaction = null;
        try (Session session = Util.getHibernateSession().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(DROP_USERS_TABLE)
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.printf("%s - ошибка удаления таблицы user_data", e.getMessage());
            }
        }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getHibernateSession().openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (HibernateException e) {
            System.out.printf("%s - ошибка сохранения пользователя с именем - %s", e.getMessage(), name);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getHibernateSession().openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
        } catch (HibernateException e) {
            System.out.printf("%s - ошибка удаления пользователя с Id - %s", e.getMessage(), id);
            if (transaction != null) {
                transaction.rollback();
            }
        }


    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        Transaction transaction = null;
        try (Session session = Util.getHibernateSession().openSession()) {
            transaction = session.beginTransaction();
            users = session.createQuery(GET_ALL_USERS).list();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.printf("%s - ошибка получения всех пользователей из таблицы user_data", e.getMessage());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getHibernateSession().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(CLEAN_USERS_TABLE)
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.printf("%s - ошибка удаления всех пользователей из таблицы user_data", e.getMessage());

        }
    }

}
