package org.example.propers.DatabaseInit;

import org.example.propers.database.Database;

import java.io.*;

public class DatabaseInitService {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/sql/init_db.sql"))) {
            StringBuffer sb = new StringBuffer();
            reader.lines().forEach(sb::append);
            database.executeUpdate(sb.toString());
            database.closeConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
