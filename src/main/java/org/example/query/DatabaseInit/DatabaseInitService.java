package org.example.query.DatabaseInit;

import org.example.database.Database;
import org.example.readFiles.SqlFileReader;

public class DatabaseInitService {
   static Database database = Database.getInstance();
    public static int dataBaseInitService(){
        int statement = database.executeUpdate(SqlFileReader.sqlReaderFile("src/main/resources/sql/init_db.sql"));
        database.closeConnection();
        return statement;
    }
    public static void main(String[] args) {
        DatabaseInitService.dataBaseInitService();
    }
}
