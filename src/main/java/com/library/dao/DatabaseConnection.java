package com.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USER = "root";
    private static final String PASSWORD = "user";

    public static Connection getConnection() {
        try{
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database successfully!!");
            return connection;
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database" + e.getMessage());
            return null;
        }
    }
}
