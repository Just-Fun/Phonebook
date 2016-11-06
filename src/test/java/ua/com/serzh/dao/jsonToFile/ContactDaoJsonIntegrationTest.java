package ua.com.serzh.dao.jsonToFile;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.serzh.entities.Contact;
import ua.com.serzh.entities.User;
import ua.com.serzh.help.CreateEntity;
import ua.com.serzh.utils.Utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Serzh on 11/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ("classpath:json-test-application-context.xml"))
public class ContactDaoJsonIntegrationTest {

    private static String testContactsFile = "src/test/resources/json/test-contacts.json";

    @Autowired
    private ContactDaoJSON contactDaoJSON;

    @Autowired
    private UserStore userStore;

    @Autowired
    private Utils utils;

    @Autowired
    private MapperObjectJson mapper;

    @BeforeClass
    public static void init() throws IOException {
        cleanFile();
    }

    @Before
    public void setup() {
        addNewContact();
    }

    @After
    public void clean() {
        cleanFile();
        contactDaoJSON.cleanContactStore();
    }

    @Test
    public void shouldAutowiredDependencies() {
        assertNotNull(contactDaoJSON);
        assertNotNull(userStore);
        assertNotNull(utils);
        assertNotNull(mapper);
    }

    @Test
    public void searchContactById() throws Exception {
        Contact contact = contactDaoJSON.searchContactById(1);
        assertEquals("Angelina", contact.getName());
    }

    @Test
    public void addContact() throws Exception {

        ContactStore contactStore = (ContactStore) mapper.getObjectFromFile(testContactsFile, ContactStore.class.getName());

        assertEquals(2, contactStore.getCountContacts());
        assertEquals("contacts", contactStore.getName());

        List<Contact> users = contactStore.getContacts();

        Contact joli = users.get(0);
        assertEquals("1", joli.getContactId().toString());
        assertEquals("Joli", joli.getSurname());
        assertEquals("Angelina", joli.getName());
        assertEquals("Petrovna", joli.getPatronymic());
        assertEquals("+380664563345", joli.getMobileNumber());
        assertEquals("+380444563345", joli.getHomePhone());
        assertEquals("Kyiv, Sviatkova 15", joli.getAddress());
        assertEquals("joli@.gmail.com", joli.getEmail());
        assertEquals("1", joli.getUserId().toString());
    }

    @Test
    public void addTwoContactsTest() throws Exception {

        ContactStore contactStore = (ContactStore) mapper.getObjectFromFile(testContactsFile, ContactStore.class.getName());

        assertEquals(2, contactStore.getCountContacts());

        List<Contact> users = contactStore.getContacts();

        Contact joli = users.get(0);
        assertEquals("1", joli.getContactId().toString());
        assertEquals("Joli", joli.getSurname());
        assertEquals("Angelina", joli.getName());
        assertEquals("1", joli.getUserId().toString());

        Contact joli2 = users.get(1);
        assertEquals("2", joli2.getContactId().toString());
        assertEquals("Joli2", joli2.getSurname());
        assertEquals("Angelina2", joli2.getName());
        assertEquals("1", joli2.getUserId().toString());
    }

    @Test
    public void allUserContacts() throws Exception {

        List<Contact> contacts = contactDaoJSON.allUserContacts(1);
        assertEquals(2, contacts.size());
        assertEquals("Joli", contacts.get(0).getSurname());
        assertEquals("Joli2", contacts.get(1).getSurname());
    }

    @Test
    public void updateContact() throws Exception {
        Contact contact = contactDaoJSON.searchContactById(1);
        contact.setName("Cleopatra");

        contactDaoJSON.updateContact(contact);

        Contact contact1 = contactDaoJSON.searchContactById(1);
        assertEquals("Cleopatra", contact1.getName());
    }

    @Test
    public void deleteContact() throws Exception {
        Contact contact = contactDaoJSON.searchContactById(1);
        contactDaoJSON.deleteContact(contact);

        List<Contact> contacts = contactDaoJSON.allUserContacts(1);

        assertEquals(1, contacts.size());
        assertNotEquals("Joli", contacts.get(0).getSurname());
        assertEquals("Joli2", contacts.get(0).getSurname());

    }

    @Test
    public void searchContactByAnyField() throws Exception {
        List jol = contactDaoJSON.searchContactByAnyField("Jol", 1);
        List joli2 = contactDaoJSON.searchContactByAnyField("Joli2", 1);
        List petrovna = contactDaoJSON.searchContactByAnyField("Petrovna", 1);
        List email = contactDaoJSON.searchContactByAnyField("gmail.com", 1);
        List wrong = contactDaoJSON.searchContactByAnyField("Same Text", 1);
        List empty = contactDaoJSON.searchContactByAnyField(null, 1);

        assertEquals(2, jol.size());
        assertEquals(1, joli2.size());
        assertEquals(2, petrovna.size());
        assertEquals(2, email.size());
        assertEquals(0, wrong.size());
        assertEquals(0, empty.size());
    }


    private void addNewContact() {
        User user = CreateEntity.createUser1();
        user.setUserId(1);
        Contact contact = CreateEntity.createContact1(user);
        Contact contact2 = CreateEntity.createContact2(user);

        contactDaoJSON.insertContact(contact);
        contactDaoJSON.insertContact(contact2);
    }

    private static void cleanFile() {
        try {
            new FileOutputStream(testContactsFile, false).close();
//            new FileWriter(testUsersFile, false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}