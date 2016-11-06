package ua.com.serzh.dao.jsonToFile;

import org.junit.After;
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

    @After
    public void clean() {
//        cleanFile();
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

    }

    @Test
    public void addContact() throws Exception {

        User user = CreateEntity.createUser1();
        user.setUserId(1);
        Contact contact = CreateEntity.createContact1(user);

        contactDaoJSON.insertContact(contact);

        ContactStore contactStore = (ContactStore) mapper.getObjectFromFile(testContactsFile, ContactStore.class.getName());

        assertEquals(1, contactStore.getCountContacts());
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

        User user = CreateEntity.createUser1();
        user.setUserId(1);
        Contact contact = CreateEntity.createContact1(user);
        Contact contact2 = CreateEntity.createContact2(user);

        contactDaoJSON.insertContact(contact);
        contactDaoJSON.insertContact(contact2);

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

    }

    @Test
    public void updateContact() throws Exception {

    }

    @Test
    public void deleteContact() throws Exception {

    }

    @Test
    public void searchContactByAnyField() throws Exception {

    }

    @Test
    public void main() throws Exception {

    }

    /* public static void main(String[] args) {
        ContactDaoJSON contactDaoJSON = new ContactDaoJSON();

//        List<Contact> contacts = contactStore.getContacts();

/*        contacts.forEach(contact -> System.out.println("id: " + contact.getContactId() + " - " + contact.getSurname()));
        System.out.println("--------------------");*/

    //Test deleteContact
/*        Contact contact = contactDaoJSON.searchContactById(9);
        contactDaoJSON.deleteContact(contact);*/

    //Test searchContactById
    /*    Contact contact = contactDaoJSON.searchContactById(10);
        contact.setSurname("Alpachino");
        contact.setName("Some");
        contactDaoJSON.updateContact(contact);*/

    //Test allUserContacts
       /* List<Contact> list = contactDaoJSON.allUserContacts(2);
        list.forEach(contact -> System.out.println(contact.getSurname()));*/

    // Test searchContactById
       /* Contact contact = contactStore.searchContactById(2);
        System.out.println(contact.getSurname());*/

    // Test insertContact
/*        User user = CreateEntity.createUser1();
        Contact contact = CreateEntity.createContact1(user);
        contact.setSurname("PavarottiSub");
        contact.setName("LuchanoSub");

       contactDaoJSON.insertContact(contact);*/

//        writeToFile(contactStore);

    // Test searchContactByAnyField
//        List<Contact> joli = contactDaoJSON.searchContactByAnyField("Joli", 1);
   /*     List<Contact> joli = contactDaoJSON.searchContactByAnyField("luch", 1);

        for (Contact contact : joli) {
            System.out.println("id: " + contact.getContactId() + " - " + contact.getSurname());
        }*/

//      contactStore.deleteContact

/*        for (Contact contact : contacts) {
            if (contact.getContactId() == 2) {
                contact.setSurname("Aniston");
            }
        }
        contacts.forEach(contact -> System.out.println("id: " + contact.getContactId() + " - " + contact.getSurname()));

        System.out.println("Change name........");

        for (Contact contact : contacts) {
            if (contact.getContactId() == 2) {
                contact.setSurname("JoliNew");
            }
        }
        contacts.forEach(contact -> System.out.println("id: " + contact.getContactId() + " - " + contact.getSurname()));

        System.out.println("--------------------------------");*/

//        writeToFile(contactStore);
//}

    private static void cleanFile() {
        try {
            new FileOutputStream(testContactsFile, false).close();
//            new FileWriter(testUsersFile, false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}