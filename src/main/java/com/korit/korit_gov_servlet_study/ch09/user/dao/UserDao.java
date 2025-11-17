package com.korit.korit_gov_servlet_study.ch09.user.dao;

import com.korit.korit_gov_servlet_study.ch09.user.entity.User;
import com.korit.korit_gov_servlet_study.ch09.user.util.ConnectionFactory;

import java.sql.*;

public class UserDao { //싱글톤으로 되어있다.
    private static UserDao instance;

    private UserDao() {

    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }
    //user추가
    public User addUser(User user) {
        String sql = "insert into user_tb(user_id, username, password, age, create_dt) values (0, ?, ?, ?, now())";

        try(
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ) {
            ps.setString(1, user.getUsername());
            ps.setString(2,  user.getPassword());
            ps.setInt(3, user.getAge());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //username으로 유저 찾기를 구현한다.
    public <Optional> User findByUsername(String username) {
        String sql = "select user_id, username, password, age, create_dt from user_tb where username = ?";

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ?Optional.of(toUser(rs)) : null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public User toUser(ResultSet resultSet) {
        return User.builder()
                .userId(resultSet.getLong("user_id"))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .age(resultSet.getInt("age"))
                .createTime(resultSet.getTimestamp("create_dt").toLocalDateTime())
                .build();
    }
}