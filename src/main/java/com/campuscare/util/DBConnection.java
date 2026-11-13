package com.campuscare.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521:xe";

    private static final String USER =
            "grievance";

    private static final String PASSWORD =
            "grievance123";

    public static Connection getConnection() {

        try {

            Class.forName("oracle.jdbc.OracleDriver");

            return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD);

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}