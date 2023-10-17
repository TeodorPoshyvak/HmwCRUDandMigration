package org.example.query.DatabasePopulate;

import org.example.database.Database;
import org.example.readFiles.SqlFileReader;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabasePopulateService {
    PreparedStatement insertWorkerStatement;
    PreparedStatement insertClientStatement;
    PreparedStatement insertProjectStatement;
    PreparedStatement insertProjectWorkerStatement;

    public DatabasePopulateService(){
        try {
            Connection connection = Database.getConnection();
            this.insertWorkerStatement = connection.prepareStatement("INSERT INTO worker(NAME, BIRTHDAY, LEVEL, SALARY) values(?, ?, ?, ?)");
            this.insertClientStatement = connection.prepareStatement("INSERT INTO client (NAME) values(?)");
            this.insertProjectStatement = connection.prepareStatement("INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE) values (?, ?, ?)");
            this.insertProjectWorkerStatement = connection.prepareStatement("INSERT INTO project_worker (PROJECT_ID, WORKER_ID) values(?,?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void insertWorker(String name, LocalDate birthday, String level, int salary) {
        try {
            insertWorkerStatement.setString(1, name);
            insertWorkerStatement.setDate(2, Date.valueOf(birthday));
            insertWorkerStatement.setString(3, level);
            insertWorkerStatement.setInt(4, salary);
            insertWorkerStatement.addBatch();
            insertWorkerStatement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertClient(String name){
        try{
            insertClientStatement.setString(1, name);
            insertClientStatement.addBatch();
            insertClientStatement.executeBatch();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void insertProject(int ID, LocalDate startDate, LocalDate finishDate){
        try{
            insertProjectStatement.setInt(1, ID);
            insertProjectStatement.setDate(2, Date.valueOf(startDate));
            insertProjectStatement.setDate(3, Date.valueOf(finishDate));
            insertProjectStatement.addBatch();
            insertProjectStatement.executeBatch();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void insertProjectWorker(int projectsID, int workersID){
        try{
            insertProjectWorkerStatement.setInt(1, projectsID);
            insertProjectWorkerStatement.setInt(2, workersID);
            insertProjectWorkerStatement.addBatch();
            insertProjectWorkerStatement.executeBatch();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        DatabasePopulateService databasePopulateService = new DatabasePopulateService();

        String[] namesWorker = {"Ivan", "Veronika", "Taras", "Oleg", "Alex", "Andrew", "Anna", "Serhii", "Olga", "Petro"};
        LocalDate[] localDates = {LocalDate.parse("1997-01-01"),
                                  LocalDate.parse("2001-02-02"),
                                  LocalDate.parse("1987-03-03"),
                                  LocalDate.parse("2000-04-04"),
                                  LocalDate.parse("1999-05-05"),
                                  LocalDate.parse("1997-06-06"),
                                  LocalDate.parse("1988-07-07"),
                                  LocalDate.parse("1989-08-08"),
                                  LocalDate.parse("1989-09-09"),
                                  LocalDate.parse("1997-10-10")};
        String[] levels = {"Trainee","Junior","Senior","Middle","Trainee","Junior","Senior","Middle","Middle","Junior"};
        int[] salaryWorker = {300, 1000, 5500,3000,600,1200,6800,3500,3500,2000};

        for(int i = 0; i < namesWorker.length; i++){
          databasePopulateService.insertWorker(namesWorker[i], localDates[i], levels[i], salaryWorker[i]);
        }


        String[] nameClient = {"Oliver","Jack","Harry","Thomas","James","Daniel"};

        for(int i = 0; i < nameClient.length; i++){
            databasePopulateService.insertClient(nameClient[i]);
        }


        int[] clientId = {1,1,1,3,2,3,3,5,4,3};
        LocalDate[] startDate = {LocalDate.parse("2001-01-01"),
                                  LocalDate.parse("2004-04-04"),
                                  LocalDate.parse("2006-03-03"),
                                  LocalDate.parse("2000-01-08"),
                                  LocalDate.parse("2014-02-05"),
                                  LocalDate.parse("2016-07-06"),
                                  LocalDate.parse("2000-04-07"),
                                  LocalDate.parse("2020-12-10"),
                                  LocalDate.parse("2017-01-10"),
                                  LocalDate.parse("2023-03-04"),
        };
        LocalDate[] finishDate = {LocalDate.parse("2023-01-01"),
                                  LocalDate.parse("2024-01-05"),
                                  LocalDate.parse("2023-05-03"),
                                  LocalDate.parse("2022-02-02"),
                                  LocalDate.parse("2023-01-02"),
                                  LocalDate.parse("2026-01-09"),
                                  LocalDate.parse("2021-05-11"),
                                  LocalDate.parse("2022-07-02"),
                                  LocalDate.parse("2035-12-01"),
                                  LocalDate.parse("2024-06-05"),
        };

        for(int i = 0; i < clientId.length; i++){
            databasePopulateService.insertProject(clientId[i], startDate[i], finishDate[i]);
        }

        int[] projectsID = {1,1,1,2,2,2,3,3,4,4,4,5,6,6,7,7,8,9,9,10,10,10,10,10};
        int[] workersID =  {1,4,2,2,4,10,7,3,1,2,9,3,9,5,6,8,7,9,10,1,2,3,4,5};

        for(int i = 0; i < projectsID.length; i++){
            databasePopulateService.insertProjectWorker(projectsID[i], workersID[i]);
        }
    }
}
