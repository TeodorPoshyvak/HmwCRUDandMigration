package org.example.query.DatabaseQuery;

import org.example.database.Database;
import org.example.query.DatabaseQuery.entities.LongestProject;
import org.example.query.DatabaseQuery.entities.MaxProjectsClient;
import org.example.query.DatabaseQuery.entities.MaxSalaryWorker;
import org.example.query.DatabaseQuery.entities.YoungestEldestWorker;
import org.example.readFiles.SqlFileReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DatabaseQueryService {
    Connection connection = Database.getConnection();
    PreparedStatement preparedStatementFindLongestProject;
    PreparedStatement preparedStatementFindMaxProjectsClient;
    PreparedStatement preparedStatementFindMaxSalaryWorker;
    PreparedStatement preparedStatementFindYoungestEldestWorkers;

    {
        try {
            this.preparedStatementFindLongestProject = connection.prepareStatement(SqlFileReader.sqlReaderFile("src/main/resources/sql/find_longest_project.sql"));
            this.preparedStatementFindMaxProjectsClient = connection.prepareStatement(SqlFileReader.sqlReaderFile("src/main/resources/sql/find_max_projects_client.sql"));
            this.preparedStatementFindMaxSalaryWorker = connection.prepareStatement(SqlFileReader.sqlReaderFile("src/main/resources/sql/find_max_salary_worker.sql"));
            this.preparedStatementFindYoungestEldestWorkers = connection.prepareStatement(SqlFileReader.sqlReaderFile( "src/main/resources/sql/find_youngest_eldest_workers.sql"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  List<LongestProject> findLongestProject() throws SQLException {
            List<LongestProject> list = new ArrayList<>();
        try(ResultSet resultSet = preparedStatementFindLongestProject.executeQuery()) {
            while (resultSet.next()) {
                LongestProject longestProject = new LongestProject(resultSet.getString("project_name"),
                                                                   resultSet.getInt("count_mounth"));
                list.add(longestProject);
            }
            resultSet.close();
            return list;
        }catch (SQLException e){
            throw new SQLException(e);
        }
    }

    public List<MaxProjectsClient> findMaxProjectsClient() throws SQLException {
                List<MaxProjectsClient> list = new ArrayList<>();

                try(ResultSet resultSet = preparedStatementFindMaxProjectsClient.executeQuery()) {

                    while (resultSet.next()) {
                        MaxProjectsClient maxProjectsClient = new MaxProjectsClient(resultSet.getString("name"),
                                                                                    resultSet.getInt("project_count"));
                        list.add(maxProjectsClient);
                    }

                    resultSet.close();
                    return list;
                }catch (SQLException e){
                    throw new SQLException(e);
                }
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() throws SQLException {
                List<MaxSalaryWorker> list = new ArrayList<>();
                try(ResultSet resultSet = preparedStatementFindMaxSalaryWorker.executeQuery()) {

                    while (resultSet.next()) {
                        MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker(resultSet.getString("name"),
                                                                              resultSet.getInt("SALARY"));
                        list.add(maxSalaryWorker);
                    }
                    resultSet.close();
                    return list;
                }catch (SQLException e){
                    throw new SQLException(e);
                }
    }


    public List<YoungestEldestWorker> findYoungestEldestWorkers() throws SQLException {
            List<YoungestEldestWorker> list = new ArrayList<>();
            try(ResultSet resultSet = preparedStatementFindYoungestEldestWorkers.executeQuery()) {
                while (resultSet.next()) {
                    YoungestEldestWorker youngestEldestWorker = new YoungestEldestWorker(resultSet.getString("TYPE"),
                                                                                         resultSet.getString("NAME"),
                                                                                         LocalDate.parse(resultSet.getString("BIRTHDAY")));
                    list.add(youngestEldestWorker);
                }
                resultSet.close();
                return list;
            }catch (SQLException e){
                throw new SQLException(e);
            }
    }

    public static void main(String[] args) throws SQLException {
        DatabaseQueryService databaseQueryService = new DatabaseQueryService();

        List<LongestProject> list = databaseQueryService.findLongestProject();
        list.forEach(System.out::println);

        List<MaxProjectsClient> list1 = databaseQueryService.findMaxProjectsClient();
        list1.forEach(System.out::println);

        List<MaxSalaryWorker> list2 = databaseQueryService.findMaxSalaryWorker();
        list2.forEach(System.out::println);

        List<YoungestEldestWorker> list3 = databaseQueryService.findYoungestEldestWorkers();
        list3.forEach(System.out::println);

    }
}
