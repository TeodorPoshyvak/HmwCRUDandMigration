package org.example.query.DatabaseInit;

import org.example.database.Database;
import org.example.readFiles.SqlFileReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitService {
    private PreparedStatement preparedStatementInitDb;

    public DatabaseInitService(){
        try {
            Connection connection = Database.getConnection();
            this.preparedStatementInitDb = connection.prepareStatement(SqlFileReader.sqlReaderFile("src/main/resources/sql/init_db.sql"));
        } catch (SQLException e){
        throw new RuntimeException(e);
        }
    }

    public void dataBaseInitService(){
        try {
           preparedStatementInitDb.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        DatabaseInitService databaseInitService = new DatabaseInitService();
        databaseInitService.dataBaseInitService();
    }
}
