package ua.com.serzh.dao.jsonToFile;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.serzh.dao.ContactDao;
import ua.com.serzh.entities.Contact;
import ua.com.serzh.utils.Utils;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by Serzh on 11/4/16.
 */
//@Component
public class ContactDaoJSON implements ContactDao {
    /* public ContactDaoJSON() {
    }*/

    private Utils utils;
    private  MapperObjectJson mapper;

    private  ContactStore contactStore;
    private String pathUsersJson;

    @Autowired/*(required = false)*/
    public ContactDaoJSON(Utils utils, MapperObjectJson mapper) throws IOException {
        this.utils = utils;
        this.mapper = mapper;

        Properties properties = utils.getProperties();
        pathUsersJson = properties.getProperty("path_contactcs.json");

//        MapperObjectJson mapper = new MapperObjectJson();

        String mappingClassName = ContactStore.class.getName();

        contactStore = (ContactStore) mapper.getObjectFromFile(pathUsersJson, mappingClassName);

        if (contactStore == null) {
            contactStore = new ContactStore();
        }
    }

    @Override
    public Contact searchContactById(int contactId) {
        return contactStore.searchContactById(contactId);
    }

    @Override
    public void insertContact(Contact contact) {
        contactStore.insertContact(contact);
        mapper.writeJsonToFile(contactStore, pathUsersJson);
    }

    @Override
    public List<Contact> allUserContacts(int userId) {
        List<Contact> contacts = contactStore.allUserContacts(userId);
        return sortContacts(contacts);
    }

    @Override
    public void updateContact(Contact contactNew) {
        contactStore.updateContact(contactNew);
        mapper.writeJsonToFile(contactStore, pathUsersJson);
    }

    @Override
    public void deleteContact(Contact contactToDel) {
        contactStore.deleteContact(contactToDel);
        mapper.writeJsonToFile(contactStore, pathUsersJson);
    }

    @Override
    public List searchContactByAnyField(String searchQuery, Integer userId) {
        List<Contact> contacts = contactStore.searchContactByAnyField(searchQuery, userId);
        return sortContacts(contacts);
    }

    private List<Contact> sortContacts(List<Contact> contacts) {
        Comparator<Contact> comparator = Comparator
                .comparing(Contact::getSurname)
                .thenComparing(Contact::getName);

        List<Contact> result = contacts.parallelStream()
                .sorted(comparator)
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
