package com.shuiyujie.generator.utils;

import com.shuiyujie.generator.source.MyConfiguration;

import java.sql.*;

/**
 * 数据库连接和关闭
 * created by shui 2017/8/19
 */
public class DBUtil {

    static {
        try {
            Class.forName(MyConfiguration.getString("driverClassName"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启数据库连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            String jdbcUrl = MyConfiguration.getString("url");
            String userName = MyConfiguration.getString("username");
            String password = MyConfiguration.getString("password");
            conn = DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     * @param stat
     * @param resultSet
     */
    public static void closeConn(Connection conn, Statement stat, ResultSet resultSet) {
        try {
            if (conn != null)
                conn.close();
            if (stat != null)
                stat.close();
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
