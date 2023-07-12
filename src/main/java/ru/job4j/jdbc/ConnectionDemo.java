package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static final String PASSWORD = "password";
    public static final String LOGIN = "username";
    public static final String URL = "url";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String app = "C:\\projects\\job4j_design\\app.properties";
        Config config = new Config(app);
        config.load();
        try (Connection connection = DriverManager.getConnection(config.value(URL), config.value(LOGIN), config.value(PASSWORD))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }

    }
}