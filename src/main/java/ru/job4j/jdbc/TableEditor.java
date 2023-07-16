package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    public static final String PASSWORD = "password";
    public static final String LOGIN = "username";
    public static final String URL = "url";
    public static final String DRIVER = "driver_class";

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty(DRIVER));
        connection = DriverManager.getConnection(properties.getProperty(URL), properties.getProperty(LOGIN),
                properties.getProperty(PASSWORD));
    }

    private void executeSql(Statement statement, String statementValue) {
        try {
            statement.execute(statementValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            executeSql(statement, String.format("CREATE TABLE IF NOT EXISTS %s ()", tableName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            executeSql(statement, String.format("DROP TABLE %s", tableName));
            System.out.println("table drop");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = connection.createStatement()) {
            executeSql(statement, String.format("ALTER TABLE %s ADD COLUMN %s %s", tableName, columnName, type));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            executeSql(statement, String.format("ALTER TABLE %s DROP COLUMN %s", tableName, columnName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
            executeSql(statement, String.format("ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName,
                    newColumnName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
            try (TableEditor tableEditor = new TableEditor(properties)) {
                tableEditor.createTable("concert");
                System.out.println(tableEditor.getTableScheme("concert"));
                tableEditor.addColumn("concert", "id", "serial primary key");
                System.out.println(tableEditor.getTableScheme("concert"));
                tableEditor.addColumn("concert", "name", "varchar(255)");
                System.out.println(tableEditor.getTableScheme("concert"));
                tableEditor.addColumn("concert", "area", "int");
                System.out.println(tableEditor.getTableScheme("concert"));
                tableEditor.dropColumn("concert", "area");
                System.out.println(tableEditor.getTableScheme("concert"));
                tableEditor.dropTable("concert");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}