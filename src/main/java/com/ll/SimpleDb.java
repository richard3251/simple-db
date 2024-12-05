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
        // JDBC URL 구성
        String url = String.format("jdbc:mysql://%s/%s?useSSL=false&serverTimezone=UTC", host, dbName);
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
