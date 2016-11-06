package ua.com.serzh.dao.jsonToFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.serzh.dao.ContactDao;
import ua.com.serzh.entities.Contact;
import ua.com.serzh.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Serzh on 11/4/16.
 */
public class ContactDaoJSON implements ContactDao {
     public ContactDaoJSON() {
    }
    private String pathContactsFile;
    String fileName = "path_contacts.json";

    private Utils utils;
    private MapperObjectJson mapper;

    private ContactStore contactStore;

    @Autowired
    public ContactDaoJSON(Utils utils, MapperObjectJson mapper) throws IOException {
        this.utils = utils;
        this.mapper = mapper;

        setup();
    }

    private void setup() throws IOException {

        pathContactsFile = utils.getProperties(fileName);

        String mappingClassName = ContactStore.class.getName();
        contactStore = (ContactStore) mapper.getObjectFromFile(pathContactsFile, mappingClassName);

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
        mapper.writeJsonToFile(contactStore, pathContactsFile);
    }

    @Override
    public List<Contact> allUserContacts(int userId) {
        List<Contact> contacts = contactStore.allUserContacts(userId);
        return sortContacts(contacts);
    }

    @Override
    public void updateContact(Contact contactNew) {
        contactStore.updateContact(contactNew);
        mapper.writeJsonToFile(contactStore, pathContactsFile);
    }

    @Override
    public void deleteContact(Contact contactToDel) {
        contactStore.deleteContact(contactToDel);
        mapper.writeJsonToFile(contactStore, pathContactsFile);
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

    // for tests
    public void cleanContactStore() {
        contactStore.setCountContacts(0);
        contactStore.setContacts(new ArrayList<>());
    }
}
