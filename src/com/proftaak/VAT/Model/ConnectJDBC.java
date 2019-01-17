package com.proftaak.VAT.Model;

import java.sql.*;
import java.util.Properties;


public class ConnectJDBC {

    private static Connection connection;

    /**
     * @return database connection
     */
    public static  Connection connect() {
        if (connection != null) {
            return connection;
        }


        /**
         * DB Credentials
         */
        String dbName = "VATdb";
        String userName = "root";
        String password = "";


        Properties properties = new Properties();
        properties.put("database", dbName);
        properties.put("user", userName);
        properties.put("password", password);
        properties.put("serverTimeZone", "UTC");


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, properties);
            return connection;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}