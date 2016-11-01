package ua.com.serzh.entities;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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

   /* public void insertNamedParameter(Contact contact){

        String sql = "INSERT INTO CUSTOMER " +
                "(CUST_ID, NAME, AGE) VALUES (:custId, :name, :age)";

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("custId", customer.getCustId());
        parameters.put("name", customer.getName());
        parameters.put("age", customer.getAge());

        getSimpleJdbcTemplate().update(sql, parameters);

    }*/

    /*public static final RowUnmapper<Contact> ROW_UNMAPPER = new RowUnmapper<Comment>() {
        @Override
        public Map<String, Object> mapColumns(Comment comment) {
            Map<String, Object> mapping = new LinkedHashMap<String, Object>();
            mapping.put("id", comment.getId());
            mapping.put("user_name", comment.getUserName());
            mapping.put("contents", comment.getContents());
            mapping.put("created_time", new java.sql.Timestamp(comment.getCreatedTime().getTime()));
            mapping.put("favourite_count", comment.getFavouriteCount());
            return mapping;
        }
    };*/
}
