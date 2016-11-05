package ua.com.serzh.entities;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Serzh on 10/27/16.
 */
public class ContactRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();

        contact.setContactId(rs.getInt("contact_id"));
        contact.setSurname(rs.getString("surname"));
        contact.setName(rs.getString("name"));
        contact.setPatronymic(rs.getString("patronymic"));
        contact.setMobileNumber(rs.getString("mobile_number"));

        contact.setHomePhone(rs.getString("home_phone"));
        contact.setAddress(rs.getString("address"));
        contact.setEmail(rs.getString("email"));

        contact.setUserId(rs.getInt("user_id"));

        return contact;
    }
}
