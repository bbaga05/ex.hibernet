package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:postgresql://localhost:5432/java_ee_jdbs";
    private static final String USER = "postgres";
    private static final String PASSWORD = "bogdaN228337";

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e){
            System.out.println("Ошибка подключения к бд");
            e.printStackTrace();
        }  return connection;
    }
}
