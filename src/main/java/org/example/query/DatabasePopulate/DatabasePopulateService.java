package org.example.query.DatabasePopulate;

import org.example.database.Database;
import org.example.readFiles.SqlFileReader;

public class DatabasePopulateService {
    static Database database = Database.getInstance();

    public static int dataBasePopulateService(){
        int statement = database.executeUpdate(SqlFileReader.sqlReaderFile("src/main/resources/sql/populate_db.sql"));
        database.closeConnection();
        return statement;
    }
    public static void main(String[] args) {
        DatabasePopulateService.dataBasePopulateService();
    }
}
