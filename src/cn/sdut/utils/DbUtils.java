package cn.sdut.utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by liuzhichao on 2018/9/4.
 */
public class DbUtils {

    private static Properties pros = new Properties();

    static {
        try {
            pros.load(new FileReader("src/db.properties"));
            Class.forName(pros.getProperty("driver"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); 
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(pros.getProperty("url"),
                    pros.getProperty("username"), pros.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeAll(ResultSet rs, Statement stm, Connection conn) {
        try {
            if( rs!=null ) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;
        try {
            if( stm!=null ) {
                stm.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if( conn!=null ) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
