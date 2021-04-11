package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DUserDao extends UserDao {
    // 1. DB 연결을 위한 Connection 가져오기
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        // DUserDao를 사용하는 사람의 connection 정보
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/tobyspring_db", "admin", "admin123");
        return c;
    }
}
