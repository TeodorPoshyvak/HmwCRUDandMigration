package org.example.database;

import org.example.readFiles.PropertyReader;
import org.flywaydb.core.Flyway;

import java.sql.*;

public class Database {
    private static final Database INSTANCE = new Database();
    private static Connection connection;

    private Database() {
        String url = PropertyReader.getConnectionUrlForPostgres();
        String user = PropertyReader.getUserForPostgres();
        String password = PropertyReader.getPasswordForPostgres();

        Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
        flyway.migrate();
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

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

