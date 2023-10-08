package org.example.propers.propers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public static String getConnectionUrlForPostgres() {
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties properties = new Properties();

            if (input == null) {
                System.out.println("Error Connection PostgresSQL");
                return null;
            }
            properties.load(input);

            return new StringBuilder("jdbc:postgresql://")
                    .append(properties.getProperty("postgres.db.host"))
                    .append(":")
                    .append(properties.getProperty("postgres.db.port"))
                    .append("/")
                    .append(properties.getProperty("postgres.db.database"))
                    .append("?currentSchema=public")
                    .toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUserForPostgres() {
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties properties = new Properties();

            if (input == null) {
                System.out.println("Error, not found User for Postgres");
                return null;
            }

            properties.load(input);

            return properties.getProperty("postgres.db.username");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPasswordForPostgres() {
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties properties = new Properties();

            if (input == null) {
                System.out.println("Error, not found PASSWORD For Postgres");
                return null;
            }
            properties.load(input);

            return properties.getProperty("postgres.db.password");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        PropertyReader.getPasswordForPostgres();
        String res = PropertyReader.getConnectionUrlForPostgres();
        System.out.println(res);
    }
}
