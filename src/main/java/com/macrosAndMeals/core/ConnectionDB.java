package com.macrosAndMeals.core;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionDB {
    private static Connection conn;

    public static Connection getConnection() {
        String user;
        String password;
        String host;
        String name;

        if (conn != null) {
            return conn;
        }

        try {

            user            = System.getenv("DB_USERNAME");
            password        = System.getenv("DB_PASSWORD");
            host            = System.getenv("DB_HOST");
            name            = System.getenv("DB_NAME");

            if (user == null || password == null || host == null || name == null) {
                throw new IllegalArgumentException(
                        "Environment variables not set.");
            }

            conn = DriverManager.getConnection("jdbc:mysql://"
                    + host + "/" + name + "?useSSL=false", user, password);
            return conn;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
