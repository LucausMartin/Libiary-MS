package com.Database;

import java.sql.*;

public class DB {
    private static final String url = "jdbc:mysql://localhost:3306/Librarydb?" +
            "useUnicode=true&characterEncoding=utf-8&useSSL=true";
    private static final String username = "root";
    private static final String password = "Lucaus260268mzx";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //查询公共方法
    public static ResultSet executeQuery(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql).executeQuery();
    }

    //增删改公共方法
    public static void executeUpdate(Connection connection, String sql) throws SQLException {
        connection.prepareStatement(sql).executeUpdate();
    }

    //关闭连接
    public static boolean closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                return true;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}