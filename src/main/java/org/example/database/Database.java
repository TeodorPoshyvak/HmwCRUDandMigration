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
        try (PreparedStatement  preparedStatement = connection.prepareStatement(query)) {
            return preparedStatement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

