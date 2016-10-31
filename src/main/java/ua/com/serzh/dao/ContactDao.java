package ua.com.serzh.dao;

import org.springframework.beans.factory.InitializingBean;
import ua.com.serzh.entities.Contact;

import java.util.List;

/**
 * Created by Serzh on 10/31/16.
 */
public interface ContactDao extends InitializingBean {
    void insertContact(Contact contact);

    List<Contact> searchContactByName(String name, int userId);

    Contact searchContactById(int contactId);

    // 2nd variant, just in case
    Contact searchContactById2(int contactId);

    List<Contact> allUserContacts(int userId);

    void updateContact(Contact contact);

    void deleteContact(Contact contact);
}
