package com.ll;

import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@RequiredArgsConstructor
public class SimpleDb {
    private final String host;
    private final String username;
    private final String password;
    private final String dbName;

    private Connection getConnection() throws SQLException {
        // JDBC URL을 구성
        // "jdbc:mysql://호스트/데이터베이스이름?옵션들" 형태로 작성
        String url = String.format("jdbc:mysql://%s/%s?useSSL=false&serverTimezone=UTC", host, dbName);

        // DriverManager를 사용해 데이터베이스와 연결된 Connection 객체를 생성
        return DriverManager.getConnection(url, username, password);
    }

    public void run(String sql) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql); // SQL 문 실행
        } catch (SQLException e) {
            throw new RuntimeException("DB 작업 중 오류 발생: " + e.getMessage(), e);
        }
    }


}
