//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.iva.dao;

import com.iva.entities.User;
import com.iva.mysql_connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static String INSERT_SQL = "INSERT INTO users (name, password) VALUES (?,?)";
    private static String SEARCH_BY_NAME_AND_PASSWORD_SQL = "SELECT user_id, name, password FROM users WHERE name = ? AND password = ?";
    private static String SEARCH_BY_NAME_SQL = "SELECT user_id, name, password FROM users WHERE name = ?";

    public UserDao() {
    }

    public void insertUser(User user) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            executeInsertStatement(connection, user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeInsertStatement(Connection connection, User user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
        ps.setString(1, user.getName());
        ps.setString(2, user.getPassword());
        ps.executeUpdate();
    }

    public User searchByNameAndPassword(String name, String password) {
        User user = null;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement ps = executeSearchByNameAndPasswordStatement(connection, name, password);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private PreparedStatement executeSearchByNameAndPasswordStatement(Connection connection,
                                                                      String name,
                                                                      String password) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SEARCH_BY_NAME_AND_PASSWORD_SQL);
        ps.setString(1, name);
        ps.setString(2, password);
        return ps;
    }

    public User searchByName(String name) {
        User user = null;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement ps = this.executeSearchByNameStatement(connection, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setName(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private PreparedStatement executeSearchByNameStatement(Connection connection, String name) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SEARCH_BY_NAME_SQL);
        ps.setString(1, name);
        return ps;
    }
}
