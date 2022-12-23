package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Util {
    private static final String URL = "jdbc:mysql://localhost/MySQL";
    private static final String USERNAME = "red";
    private static final String PASSWORD = "L{zvjY4lkNcfouE";

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
}
//"jdbc:sqlserver://localhost"), props.getProperty("red"), props.getProperty("L{zvjY4lkNcfouE"