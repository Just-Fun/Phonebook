package ua.com.serzh.dao;

import ua.com.serzh.entities.Contact;

import java.util.List;

import static ua.com.serzh.dao.MapperObjectJson.writeJsonToFile;

/**
 * Created by Serzh on 11/4/16.
 */
public /*abstract*/ class ContactDaoJSON implements ContactDao {

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    ContactStore contactStore;

    static String pathName = "src/main/resources/json/contacts.json";

    public ContactDaoJSON() {
        String mappingClassName = ContactStore.class.getName();
        contactStore = (ContactStore) MapperObjectJson.getObjectFromFile(pathName, contactStore, mappingClassName);
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
        writeJsonToFile(contactStore, pathName);
    }

    @Override
    public List<Contact> allUserContacts(int userId) {
        return contactStore.allUserContacts(userId);
    }

    @Override
    public void updateContact(Contact contactNew) {
        contactStore.updateContact(contactNew);
        writeJsonToFile(contactStore, pathName);
    }

    @Override
    public void deleteContact(Contact contactToDel) {
        contactStore.deleteContact(contactToDel);
        writeJsonToFile(contactStore, pathName);
    }

    @Override
    public List searchContactByAnyField(String searchQuery, Integer userId) {
        return contactStore.searchContactByAnyField(searchQuery, userId);
    }
}
