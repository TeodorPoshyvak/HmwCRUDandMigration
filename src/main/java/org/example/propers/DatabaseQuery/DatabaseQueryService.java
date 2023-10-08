package org.example.propers.DatabaseQuery;

import org.example.propers.database.Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DatabaseQueryService {
    public static List<LongestProject> findLongestProject() throws IOException {
        try (Statement statement = Database.getConnection().createStatement()) {
            List<LongestProject> list = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/sql/find_longest_project.sql"));
            StringBuffer sb = new StringBuffer();
            br.lines().forEach(sb::append);

            ResultSet resultSet = statement.executeQuery(sb.toString());
            while (resultSet.next()) {
                LongestProject longestProject = new LongestProject(resultSet.getString("project_name"), resultSet.getInt("count_mounth"));
                list.add(longestProject);
            }

            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static List<MaxProjectsClient> findMaxProjectsClient() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/sql/find_max_projects_client.sql"))) {
            Statement statement = Database.getConnection().createStatement();
            List<MaxProjectsClient> list = new ArrayList<>();
            StringBuffer sb = new StringBuffer();
            br.lines().forEach(sb::append);

            ResultSet resultSet = statement.executeQuery(sb.toString());
            while (resultSet.next()) {
                MaxProjectsClient maxProjectsClient = new MaxProjectsClient(resultSet.getString("name"), resultSet.getInt("project_count"));
                list.add(maxProjectsClient);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<MaxSalaryWorker> findMaxSalaryWorker() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/sql/find_max_salary_worker.sql"))) {
            Statement statement = Database.getConnection().createStatement();
            List<MaxSalaryWorker> list = new ArrayList<>();
            StringBuffer sb = new StringBuffer();
            br.lines().forEach(sb::append);

            ResultSet resultSet = statement.executeQuery(sb.toString());
            while (resultSet.next()) {
                MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker(resultSet.getString("name"), resultSet.getInt("SALARY"));
                list.add(maxSalaryWorker);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<YoungestEldestWorker> findYoungestEldestWorkers() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/sql/find_youngest_eldest_workers.sql"))) {
            Statement statement = Database.getConnection().createStatement();
            List<YoungestEldestWorker> list = new ArrayList<>();
            StringBuffer sb = new StringBuffer();
            br.lines().forEach(sb::append);

            ResultSet resultSet = statement.executeQuery(sb.toString());
            while (resultSet.next()) {
                YoungestEldestWorker youngestEldestWorker = new YoungestEldestWorker(resultSet.getString("TYPE"), resultSet.getString("NAME"), LocalDate.parse(resultSet.getString("BIRTHDAY")));
                list.add(youngestEldestWorker);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        List<LongestProject> list = DatabaseQueryService.findLongestProject();
        for (LongestProject lp : list) {
            System.out.println(lp);
        }

        List<MaxProjectsClient> list1 = DatabaseQueryService.findMaxProjectsClient();
        for (MaxProjectsClient mw : list1) {
            System.out.println(mw);
        }

        List<MaxSalaryWorker> list2 = DatabaseQueryService.findMaxSalaryWorker();
        for (MaxSalaryWorker msw : list2) {
            System.out.println(msw);
        }

        List<YoungestEldestWorker> list3 = DatabaseQueryService.findYoungestEldestWorkers();
        for (YoungestEldestWorker yew : list3) {
            System.out.println(yew);
        }

    }
}
