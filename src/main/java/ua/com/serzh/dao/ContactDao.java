package ua.com.serzh.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import ua.com.serzh.entities.Contact;
import ua.com.serzh.entities.ContactRowMapper;

import java.util.List;

//@Component
public class ContactDao extends JdbcDaoSupport {

    public void insertContact(Contact contact) {
        String sql = "INSERT INTO contacts (name, mobile_number, user_id) VALUES (?, ?, ?)";
        getJdbcTemplate().update(sql, contact.getName(), contact.getMobileNumber(), contact.getUserId());
    }

    public List<Contact> searchContactByName(String name, int userId) {
        String sql = String.format("SELECT * FROM contacts WHERE user_id = %d AND name LIKE '%s%%'", userId, name);
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Contact.class));
    }

    public Contact searchContactById(int contactId) {
        String sql = "SELECT * FROM contacts WHERE contact_id = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{contactId}, new BeanPropertyRowMapper<>(Contact.class));
    }

    // 2nd variant, just in case
    public Contact searchContactById2(int contactId) {
        String sql = "SELECT * FROM contacts WHERE contact_id = ?";
        return (Contact) getJdbcTemplate().queryForObject(sql, new Object[]{contactId}, new ContactRowMapper());
    }

    public List<Contact> allUserContacts(int userId) {
        String sql = String.format("SELECT * FROM contacts WHERE user_id = %d", userId);
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Contact.class));
    }

    public void updateContact(Contact contact) {
        String sql = "UPDATE contacts SET name = ?, mobile_number = ? WHERE contact_id = ?";
        getJdbcTemplate().update(sql, contact.getName(), contact.getMobileNumber(), contact.getContactId());
    }

    public void deleteContact(Contact contact) {
        String sql = "DELETE FROM contacts WHERE contact_id = ?";
        getJdbcTemplate().update(sql, contact.getContactId());
    }
}