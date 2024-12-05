package com.ll;

import lombok.RequiredArgsConstructor;

import java.sql.*;

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

    public void run(String sql, Object... params) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]); // ? 인덱스는 1부터 시작
            }

            // SQL 실행
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute SQL: " + sql, e);
        }

    }

    public Sql genSql() {
        return new Sql();
    }


}
