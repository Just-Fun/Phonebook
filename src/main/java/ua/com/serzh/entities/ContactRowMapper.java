package ua.com.serzh.entities;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();

        contact.setContactId(rs.getInt("contact_id"));
        contact.setMobileNumber(rs.getString("mobile_number"));
        contact.setName(rs.getString("name"));
        contact.setUserId(rs.getInt("user_id"));

        return contact;
    }
}
