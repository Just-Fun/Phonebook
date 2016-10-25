//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ua.com.serzh.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import ua.com.serzh.entities.Contact;
import ua.com.serzh.entities.ContactRowMapper;
import ua.com.serzh.mysql_connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDao extends JdbcDaoSupport {

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

    public ContactDao() {
    }

    public void insertContact(Contact contact) {
        getConnection2();
        String sql = "INSERT INTO contacts (name, mobile_number, user_id) VALUES (?, ?, ?)";
        template.update(sql, new Object[]{contact.getName(), contact.getMobileNumber(), contact.getUserId()});
    }

    public List<Contact> searchContactByName(String name, int userId) {
        getConnection2();
        String sql = String.format("SELECT * FROM contacts WHERE user_id = %d AND name = '%s'", userId, name);
        return template.query(sql, new BeanPropertyRowMapper(Contact.class));
    }

    public Contact searchContactById(int contactId) {
        getConnection2();
        String sql = "SELECT * FROM contacts WHERE contact_id = ?";
        return template.queryForObject(sql, new Object[]{contactId}, new BeanPropertyRowMapper<>(Contact.class));
    }

    // just in case
    public Contact searchContactById2(int contactId) {
        getConnection2();
        String sql = "SELECT * FROM contacts WHERE contact_id = ?";
        return (Contact) template.queryForObject(sql, new Object[]{contactId}, new ContactRowMapper());
    }

    public List<Contact> allUserContacts(int userId) {
        getConnection2();
        String sql = String.format("SELECT * FROM contacts WHERE user_id = %d", userId);
        return template.query(sql, new BeanPropertyRowMapper(Contact.class));
    }

    public void updateContact(Contact contact) {
        String sql = "UPDATE contacts SET name = ?, mobile_number = ? WHERE contact_id = ?";
        try (PreparedStatement ps = getConnection2().prepareStatement(sql)) {
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getMobileNumber());
            ps.setInt(3, contact.getContactId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(Contact contact) {
        String sql = "DELETE FROM contacts WHERE contact_id = ?";
        try (PreparedStatement ps = getConnection2().prepareStatement(sql)) {
            ps.setInt(1, contact.getUserId()); // TODO check, hope its right, before was empty...
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
