package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://localhost";
    private static final String LOGIN = "red";
    private static final String PASSWORD = "L{zvjY4lkNcfouE";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(HOST, LOGIN, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
//"jdbc:sqlserver://localhost"), props.getProperty("red"), props.getProperty("L{zvjY4lkNcfouE"