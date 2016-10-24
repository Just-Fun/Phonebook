package ua.com.serzh.entities;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();

        contact.setContactId(rs.getInt("contactId"));
        contact.setName(rs.getString("name"));
        contact.setMobileNumber(rs.getString("mobileNumber"));
        contact.setUserId(rs.getInt("userId"));

        return contact;
    }
}
