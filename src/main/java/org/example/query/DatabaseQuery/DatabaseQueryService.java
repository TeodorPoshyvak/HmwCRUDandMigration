package org.example.query.DatabaseQuery;

import org.example.database.Database;
import org.example.entities.LongestProject;
import org.example.entities.MaxProjectsClient;
import org.example.entities.MaxSalaryWorker;
import org.example.entities.YoungestEldestWorker;
import org.example.readFiles.SqlFileReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DatabaseQueryService {
    static Database instance = Database.getInstance();

    public static List<LongestProject> findLongestProject() throws SQLException {
            List<LongestProject> list = new ArrayList<>();
            ResultSet resultSet = instance.executeResult((SqlFileReader.sqlReaderFile("src/main/resources/sql/find_longest_project.sql")));
            while (resultSet.next()) {
                LongestProject longestProject = new LongestProject(resultSet.getString("project_name"), resultSet.getInt("count_mounth"));
                list.add(longestProject);
            }
            resultSet.close();
            return list;
    }

    public static List<MaxProjectsClient> findMaxProjectsClient() throws SQLException {
                List<MaxProjectsClient> list = new ArrayList<>();
                ResultSet resultSet = instance.executeResult(SqlFileReader.sqlReaderFile("src/main/resources/sql/find_max_projects_client.sql"));
                while (resultSet.next()) {
                    MaxProjectsClient maxProjectsClient = new MaxProjectsClient(resultSet.getString("name"), resultSet.getInt("project_count"));
                    list.add(maxProjectsClient);
                }
                resultSet.close();
                return list;
    }

    public static List<MaxSalaryWorker> findMaxSalaryWorker() throws SQLException {
                List<MaxSalaryWorker> list = new ArrayList<>();
                ResultSet resultSet = instance.executeResult(SqlFileReader.sqlReaderFile("src/main/resources/sql/find_max_salary_worker.sql"));
                while (resultSet.next()) {
                    MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker(resultSet.getString("name"), resultSet.getInt("SALARY"));
                    list.add(maxSalaryWorker);
                }
                resultSet.close();
                return list;
    }


    public static List<YoungestEldestWorker> findYoungestEldestWorkers() throws SQLException {
            List<YoungestEldestWorker> list = new ArrayList<>();
            ResultSet resultSet = instance.executeResult(SqlFileReader.sqlReaderFile( "src/main/resources/sql/find_youngest_eldest_workers.sql"));
            while (resultSet.next()) {
                YoungestEldestWorker youngestEldestWorker = new YoungestEldestWorker(resultSet.getString("TYPE"), resultSet.getString("NAME"), LocalDate.parse(resultSet.getString("BIRTHDAY")));
                list.add(youngestEldestWorker);
            }
            resultSet.close();
            return list;
    }

    public static void main(String[] args) throws SQLException {
        List<LongestProject> list = DatabaseQueryService.findLongestProject();
        list.forEach(System.out::println);

        List<MaxProjectsClient> list1 = DatabaseQueryService.findMaxProjectsClient();
        list1.forEach(System.out::println);

        List<MaxSalaryWorker> list2 = DatabaseQueryService.findMaxSalaryWorker();
        list2.forEach(System.out::println);

        List<YoungestEldestWorker> list3 = DatabaseQueryService.findYoungestEldestWorkers();
        list3.forEach(System.out::println);

    }
}
