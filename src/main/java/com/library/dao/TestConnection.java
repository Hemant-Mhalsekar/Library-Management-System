package com.library.dao;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();

        if(conn != null ){
            System.out.println("Connection test successful");
        } else {
            System.out.println("Connection test failed");
        }
    }
}
