package ua.com.serzh.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import ua.com.serzh.entities.User;
import ua.com.serzh.mysql_connection.ConnectionFactory;

import java.sql.Connection;

public class UserDao extends JdbcDaoSupport {

    private Connection connection = null;
    private JdbcTemplate template;

    //TODO make new only firs time
    private Connection getConnection2() {
        if (connection == null) {
            connection = ConnectionFactory.getConnection();
        }
        if (template == null) {
            template = new JdbcTemplate(new SingleConnectionDataSource(connection, false));
        }
        return connection;
    }

   /* public UserDao() {
    }*/

    public void insertUser(User user) {
        getConnection2();
        String sql = "INSERT INTO users (name, password) VALUES (?,?)";
        template.update(sql, user.getName(), user.getPassword());

    }

    public User searchByNameAndPassword(String name, String password) {
        getConnection2();
        String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
        try {
            return template.queryForObject(sql, new Object[]{name, password}, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User searchByName(String name) {
        getConnection2();
        String sql = "SELECT * FROM users WHERE name = ?";
        try {
            return template.queryForObject(sql, new Object[]{name}, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}