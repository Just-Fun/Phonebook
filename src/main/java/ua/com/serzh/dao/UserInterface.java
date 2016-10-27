package ua.com.serzh.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import ua.com.serzh.entities.Contact;
import ua.com.serzh.entities.ContactRowMapper;
import ua.com.serzh.entities.User;

import java.util.List;

/**
 * Created by Serzh on 10/26/16.
 */
public interface UserInterface {
   /* void insertContact(Contact contact);

    List<Contact> searchContactByName(String name, int userId);

    Contact searchContactById(int contactId);

    Contact searchContactById2(int contactId);

    List<Contact> allUserContacts(int userId);

    void updateContact(Contact contact);

    void deleteContact(Contact contact);*/

    void insertUser(User user);

    User searchByNameAndPassword(String name, String password);

    User searchByName(String name);
}
