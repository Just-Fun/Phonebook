package ua.com.serzh.dao;

import ua.com.serzh.entities.Contact;
import ua.com.serzh.utils.Utils;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ua.com.serzh.dao.MapperObjectJson.writeJsonToFile;

/**
 * Created by Serzh on 11/4/16.
 */
//@Component
public class ContactDaoJSON implements ContactDao {

    String pathUsersJson = Utils.getProperties().getProperty("path_contactcs.json");

    ContactStore contactStore;

    public ContactDaoJSON() throws IOException {
        String mappingClassName = ContactStore.class.getName();

        contactStore = (ContactStore) MapperObjectJson.getObjectFromFile(pathUsersJson, mappingClassName);

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
        writeJsonToFile(contactStore, pathUsersJson);
    }

    @Override
    public List<Contact> allUserContacts(int userId) {
        // TODO sort
        List<Contact> contacts = contactStore.allUserContacts(userId);

        return sortContacts(contacts);
    }

    @Override
    public void updateContact(Contact contactNew) {
        contactStore.updateContact(contactNew);
        writeJsonToFile(contactStore, pathUsersJson);
    }

    @Override
    public void deleteContact(Contact contactToDel) {
        contactStore.deleteContact(contactToDel);
        writeJsonToFile(contactStore, pathUsersJson);
    }

    @Override
    public List searchContactByAnyField(String searchQuery, Integer userId) {
        // TODO sort
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
