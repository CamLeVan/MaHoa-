package com.levancam.demo6.models;

import java.sql.*;

public class ConnectDatabase {
    public static String URL = "jdbc:mysql://localhost:3306/nhansu";
    public static String USERNAME = "root";
    public static String PASSWORD = "";

    public static void main(String args[]) {
        try {
            // connnect to database 'testdb'
            Connection conn = getConnection(URL, USERNAME, PASSWORD);
            // crate statement
            Statement stmt = conn.createStatement();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static Connection getConnection(String url, String username, String password) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }


}

