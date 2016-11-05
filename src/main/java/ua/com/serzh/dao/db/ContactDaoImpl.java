package ua.com.serzh.dao.db;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import ua.com.serzh.dao.ContactDao;
import ua.com.serzh.entities.Contact;
import ua.com.serzh.entities.ContactRowMapper;

import java.util.List;

/**
 * Created by Serzh on 10/25/16.
 */
//@Component
public class ContactDaoImpl extends JdbcDaoSupport implements ContactDao {

    @Override
    public void insertContact(Contact contact) {
        String sql = "INSERT INTO contacts (surname, name, patronymic, mobile_number, home_phone, address, email, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        getJdbcTemplate().update(sql, contact.getSurname(), contact.getName(), contact.getPatronymic(), contact.getMobileNumber(),
                contact.getHomePhone(), contact.getAddress(), contact.getEmail(), contact.getUserId());
    }

 /*   @Override
    public List<Contact> searchContactByName(String name, int userId) {
        String sql = String.format("SELECT * FROM contacts WHERE user_id = %d AND name LIKE '%s%%'", userId, name);
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Contact.class));
    }*/

    @Override
    public List searchContactByAnyField(String searchQuery, Integer userId) {
        String sql = String.format("SELECT * FROM contacts WHERE user_id = '%d' AND" +
                        "(surname LIKE '%s%%' OR name LIKE '%s%%' OR patronymic LIKE '%s%%' OR mobile_number LIKE '%s%%' OR " +
                        "home_phone LIKE '%s%%' OR address LIKE '%s%%' OR email LIKE '%s%%')",
                userId, searchQuery, searchQuery, searchQuery, searchQuery, searchQuery, searchQuery, searchQuery);
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Contact.class));
    }

    @Override
    public Contact searchContactById(int contactId) {
        String sql = "SELECT * FROM contacts WHERE contact_id = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{contactId}, new BeanPropertyRowMapper<>(Contact.class));
    }

    // 2nd variant, just in case
    public Contact searchContactById2(int contactId) {
        String sql = "SELECT * FROM contacts WHERE contact_id = ?";
        return (Contact) getJdbcTemplate().queryForObject(sql, new Object[]{contactId}, new ContactRowMapper());
    }

    @Override
    public List<Contact> allUserContacts(int userId) {
        String sql = String.format("SELECT * FROM contacts WHERE user_id = %d ORDER BY surname ASC", userId);
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Contact.class));
    }

    @Override
    public void updateContact(Contact contact) {
        String sql = "UPDATE contacts SET surname = ?, name = ?, patronymic = ?, mobile_number = ?, home_phone = ?," +
                " address = ?, email = ? WHERE contact_id = ?";
        Object[] contactFields = {contact.getSurname(), contact.getName(), contact.getPatronymic(), contact.getMobileNumber(),
                contact.getHomePhone(), contact.getAddress(), contact.getEmail(), contact.getContactId()};
        getJdbcTemplate().update(sql, contactFields);
    }

    @Override
    public void deleteContact(Contact contact) {
        String sql = "DELETE FROM contacts WHERE contact_id = ?";
        getJdbcTemplate().update(sql, contact.getContactId());
    }
}