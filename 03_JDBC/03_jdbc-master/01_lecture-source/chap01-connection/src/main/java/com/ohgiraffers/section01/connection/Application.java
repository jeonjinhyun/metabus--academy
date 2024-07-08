package com.ohgiraffers.section01.connection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application {

    public static void main(String[] args) {

        Properties props = new Properties();
        Connection con = null;

        try {
            props.load(
                    new FileReader(
                            "src/main/java/com/ohgiraffers/section01/connection/jdbc-config.properties"));

            String driver = props.getProperty("driver");
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            // 클래스 동적 로딩, 사용할 드라이버 등록
            Class.forName(driver);

            con = DriverManager.getConnection(url, user, password);

            System.out.println("con = " + con);
            
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
