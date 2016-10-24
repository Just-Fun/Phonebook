//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ua.com.serzh.dao;

import ua.com.serzh.entities.Contact;
import ua.com.serzh.mysql_connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {
    private static final String INSERT_SQL = "INSERT INTO contacts (name, mobile_number, user_id) VALUES (?, ?, ?)";
    private static final String SEARCH_BY_NAME_SQL = "SELECT contact_id, name, mobile_number, user_id FROM contacts WHERE user_id = ? AND name = ?";
    private static final String RETRIEVE_CONTACT_SQL = "SELECT contact_id, name, mobile_number, user_id FROM contacts WHERE user_id = ?";
    private static final String SEARCH_BY_ID_SQL = "SELECT contact_id, name, mobile_number, user_id FROM contacts WHERE contact_id = ?";
    private static final String UPDATE_SQL = "UPDATE contacts SET name = ?, mobile_number = ? WHERE contact_id = ?";
    private static final String DELETE_SQL = "DELETE FROM contacts WHERE contact_id = ?";

    private Connection connection = null;

    private Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        return ConnectionFactory.getConnection();
    }


    public ContactDao() {
    }

    public void insertContact(Contact contact) {
        try (PreparedStatement ps = getConnection().prepareStatement(INSERT_SQL)) {
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getMobileNumber());
            ps.setInt(3, contact.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Contact> searchContactByName(String name, int userId) {
        ArrayList<Contact> contacts = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(SEARCH_BY_NAME_SQL)) {
            ps.setInt(1, userId);
            ps.setString(2, name);
            createContacts(contacts, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public Contact searchContactById(int contactId) {
        Contact contact = new Contact();
        try (PreparedStatement ps = getConnection().prepareStatement(SEARCH_BY_ID_SQL)) {
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
        try (PreparedStatement ps = getConnection().prepareStatement(RETRIEVE_CONTACT_SQL)) {
            ps.setInt(1, userId);
            createContacts(contacts, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public void updateContact(Contact contact) {
        try (PreparedStatement ps = getConnection().prepareStatement(UPDATE_SQL)) {
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getMobileNumber());
            ps.setInt(3, contact.getContactId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(Contact contact) {
        try (PreparedStatement ps = getConnection().prepareStatement(DELETE_SQL)) {
            ps.setInt(1, contact.getUserId()); // TODO check, hope its right
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createContacts(ArrayList<Contact> contacts, PreparedStatement ps) throws SQLException {
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
    }
}
