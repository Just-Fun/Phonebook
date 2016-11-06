package ua.com.serzh.dao.jsonToFile;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.serzh.utils.Utils;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Serzh on 11/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ("classpath:json-test-application-context.xml"))
public class ContactDaoJsonIntegrationTest {

    private static String testUsersFile = "src/test/resources/json/test-contacts.json";


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
//        cleanFile();
    }

    @After
    public void clean() {
//        cleanFile();
//        contactDaoJSON.cleanUserStore();
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
}