package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import jm.task.core.jdbc.model.User;


public class Util {
    private static SessionFactory sessionFactory;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "redd";
    private static final String PASSWORD = "redd";
    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";
    public static Connection getConnection() {
        Connection connection = null;

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static SessionFactory getHibernateSession() {

        if (sessionFactory == null) {

            try {
                Configuration configuration = new Configuration();

                Properties properties = new Properties();
                properties.put(Environment.URL, URL);
                properties.put(Environment.USER, USERNAME);
                properties.put(Environment.PASS, PASSWORD);
                properties.put(Environment.DRIVER, DRIVER);
                properties.put(AvailableSettings.DIALECT, DIALECT);
                properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(AvailableSettings.SHOW_SQL, "true");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.out.println("Check Util configuration!"+ e);
                e.printStackTrace();
            }

        }
        return sessionFactory;
    }


}


//
// "jdbc:sqlserver://localhost"), props.getProperty("red"), props.getProperty("L{zvjY4lkNcfouE"