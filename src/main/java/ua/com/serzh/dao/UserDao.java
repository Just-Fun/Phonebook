//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ua.com.serzh.dao;

import ua.com.serzh.entities.User;
import ua.com.serzh.mysql_connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static String INSERT_SQL = "INSERT INTO users (name, password) VALUES (?,?)";
    private static String SEARCH_BY_NAME_AND_PASSWORD_SQL = "SELECT user_id, name, password FROM users WHERE name = ? AND password = ?";
    private static String SEARCH_BY_NAME_SQL = "SELECT user_id, name, password FROM users WHERE name = ?";


    private Connection connection = null;

    private Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        return ConnectionFactory.getConnection();
    }

    public UserDao() {
    }

    public void insertUser(User user) {
        try (PreparedStatement ps = getConnection().prepareStatement(INSERT_SQL)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User searchByNameAndPassword(String name, String password) {
        User user = null;
        try (PreparedStatement ps = getConnection().prepareStatement(SEARCH_BY_NAME_AND_PASSWORD_SQL)) {
            ps.setString(1, name);
            ps.setString(2, password);
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

    public User searchByName(String name) {
        User user = null;
        try (PreparedStatement ps = getConnection().prepareStatement(SEARCH_BY_NAME_SQL)) {
            ps.setString(1, name);
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
}
