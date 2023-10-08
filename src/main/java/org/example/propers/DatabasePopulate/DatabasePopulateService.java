package org.example.propers.DatabasePopulate;

import org.example.propers.database.Database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DatabasePopulateService {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/sql/populate_db.sql"))) {
            StringBuffer sb = new StringBuffer();
            br.lines().forEach(sb::append);
            database.executeUpdate(sb.toString());

            database.closeConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
