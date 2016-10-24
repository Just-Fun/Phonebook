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
import ua.com.serzh.mysql_connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDao extends JdbcDaoSupport {
    private static final String RETRIEVE_CONTACT_SQL = "SELECT contact_id, name, mobile_number, user_id FROM contacts WHERE user_id = ?";
    private static final String SEARCH_BY_ID_SQL = "SELECT contact_id, name, mobile_number, user_id FROM contacts WHERE contact_id = ?";
    private static final String UPDATE_SQL = "UPDATE contacts SET name = ?, mobile_number = ? WHERE contact_id = ?";
    private static final String DELETE_SQL = "DELETE FROM contacts WHERE contact_id = ?";

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

        template.update("INSERT INTO contacts (name, mobile_number, user_id) VALUES (?, ?, ?)",
                new Object[]{contact.getName(), contact.getMobileNumber(), contact.getUserId()});
    }

    public List<Contact> searchContactByName(String name, int userId) {
        getConnection2();
        String query = String.format("SELECT * FROM contacts WHERE user_id = %d AND name = %s", userId, name);

        List<Contact> contacts = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Contact.class));
        return contacts;
    }

    public Contact searchContactById(int contactId) {
        Contact contact = new Contact();
        try (PreparedStatement ps = getConnection2().prepareStatement(SEARCH_BY_ID_SQL)) {
            ps.setInt(1, contactId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    contact.setContactId(rs.getInt("contact_id"));
                    contact.setName(rs.getString("name"));
                    contact.setMobileNumber(rs.getString("mobile_number"));
                    contact.setUserId(rs.getInt("user_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }

    public List<Contact> allUserContacts(int userId) {
        ArrayList<Contact> contacts = new ArrayList<>();
        try (PreparedStatement ps = getConnection2().prepareStatement(RETRIEVE_CONTACT_SQL)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Contact contact = new Contact();
                    contact.setContactId(rs.getInt("contact_id"));
                    contact.setName(rs.getString("name"));
                    contact.setMobileNumber(rs.getString("mobile_number"));
                    contact.setUserId(rs.getInt("user_id"));
                    contacts.add(contact);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public void updateContact(Contact contact) {
        try (PreparedStatement ps = getConnection2().prepareStatement(UPDATE_SQL)) {
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getMobileNumber());
            ps.setInt(3, contact.getContactId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(Contact contact) {
        try (PreparedStatement ps = getConnection2().prepareStatement(DELETE_SQL)) {
            ps.setInt(1, contact.getUserId()); // TODO check, hope its right
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
