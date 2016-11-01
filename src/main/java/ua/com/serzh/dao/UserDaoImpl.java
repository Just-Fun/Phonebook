package ua.com.serzh.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import ua.com.serzh.entities.User;

/**
 * Created by Serzh on 10/25/16.
 */
//@Component
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

    @Override
    public void insertUser(User user) {
        String sql = "INSERT INTO users (name, password) VALUES (?,?)";
        getJdbcTemplate().update(sql, user.getName(), user.getPassword());

    }

    @Override
    public User searchByNameAndPassword(String name, String password) {
        String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
        try {
            return getJdbcTemplate().queryForObject(sql, new Object[]{name, password}, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User searchByName(String name) {
        String sql = "SELECT * FROM users WHERE name = ?";
        try {
            return getJdbcTemplate().queryForObject(sql, new Object[]{name}, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}