package com.swiftify.application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ApplicationMain {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/swiftify";
        String username = "developer";
        String password = "taylorislovetaylorislife";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)){
            // Create Songs table
            String createTableSQL = "CREATE TABLE Songs ("
                    + "SongId serial PRIMARY KEY,"
                    + "Name VARCHAR(255),"
                    + "Album VARCHAR(255),"
                    + "Artist VARCHAR(255),"
                    + "DurationInSeconds INT,"
                    + "ReleaseYear INT,"
                    + "AudioUrl VARCHAR(255)"
                    + ")";

            try (Statement statement = connection.createStatement()) {
                statement.execute(createTableSQL);
                System.out.println("Table 'Songs' created successfully.");
            }

            // Insert test query into table
            String insertQuery = "INSERT INTO yourtable (SongId, Name, Album, Artist, DurationInSeconds, ReleaseYear, AudioUrl,) " +
                                 "VALUES (Shake If Off, 1989, Taylor Swift, 219, 2014, www.taylorislife.com)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, "Value1");
                preparedStatement.setString(2, "Value2");
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) inserted.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}