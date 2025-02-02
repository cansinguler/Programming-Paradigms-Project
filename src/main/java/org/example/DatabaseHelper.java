package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    private static final String URL = "jdbc:sqlite:src/main/resources/students.db";

    // Method to connect to the database
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connected to SQLite successfully.");
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
        return conn;
    }

    // Method to create the students table if it doesn’t exist
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS students ("
                + "studentID TEXT PRIMARY KEY, "
                + "name TEXT NOT NULL, "
                + "age INTEGER CHECK (age > 0), "
                + "grade REAL CHECK (grade BETWEEN 0.0 AND 100.0))";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created (if it didn’t exist).");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }
}
