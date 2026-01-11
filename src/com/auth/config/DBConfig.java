package com.auth.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {

    private static final String URL = "jdbc:postgresql://localhost:5432/authdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root@123";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
