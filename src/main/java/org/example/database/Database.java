package org.example.database;

import org.example.readFiles.PropertyReader;
import java.sql.*;

public class Database {
    private static final Database INSTANCE = new Database();
    private static Connection connection;

    private Database() {
        String url = PropertyReader.getConnectionUrlForPostgres();
        String user = PropertyReader.getUserForPostgres();
        String password = PropertyReader.getPasswordForPostgres();

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public static Connection getConnection() {
        return connection;
    }

    public int executeUpdate(String query) {
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet executeResult(String query) throws SQLException {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

