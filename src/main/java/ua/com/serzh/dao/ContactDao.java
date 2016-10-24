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

    public ContactDao() {
    }

    public void insertContact(Contact contact) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            executeInsert(contact, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeInsert(Contact contact, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
        ps.setString(1, contact.getName());
        ps.setString(2, contact.getMobileNumber());
        ps.setInt(3, contact.getUserId());
        ps.executeUpdate();

    }

    public List<Contact> searchContactByName(String name, int userId) {
        ArrayList<Contact> contacts = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement ps = executeSearchByName(name, userId, connection, SEARCH_BY_NAME_SQL);
           try( ResultSet rs = ps.executeQuery()) {
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

    private PreparedStatement executeSearchByName(String name, int userId,
                                                  Connection connection,
                                                  String searchByNameSql) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(searchByNameSql);
        ps.setInt(1, userId);
        ps.setString(2, name);
        return ps;
    }

    public Contact searchContactById(int contactId) {
        Contact contact = new Contact();
        try (Connection e = ConnectionFactory.getConnection()){
            PreparedStatement ps = this.executeSearchById(contactId, e, SEARCH_BY_ID_SQL);
            try(ResultSet rs = ps.executeQuery()) {
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

    private PreparedStatement executeSearchById(int contactId, Connection connection, String sql) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, contactId);
        return ps;
    }

    public List<Contact> allUserContacts(int userId) {
        ArrayList<Contact> contacts = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement ps = this.executeRetrieveAllUsers(userId, connection, RETRIEVE_CONTACT_SQL);
            try(ResultSet rs = ps.executeQuery()) {
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

    private PreparedStatement executeRetrieveAllUsers(int userId, Connection connection, String retrieveAllUsersSql) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(retrieveAllUsersSql);
        ps.setInt(1, userId);
        return ps;
    }

    public void updateContact(Contact contact) {
        try (Connection connection = ConnectionFactory.getConnection()){
            executeUpdate(contact, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeUpdate(Contact contact, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
        ps.setString(1, contact.getName());
        ps.setString(2, contact.getMobileNumber());
        ps.setInt(3, contact.getContactId());
        ps.executeUpdate();
    }

    public void deleteContact(Contact contact) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            executeDelete(contact, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeDelete(Contact contact, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(DELETE_SQL);
    }
}
