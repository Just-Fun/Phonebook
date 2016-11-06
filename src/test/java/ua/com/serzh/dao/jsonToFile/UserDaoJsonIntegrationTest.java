package ua.com.serzh.dao.jsonToFile;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.serzh.entities.User;
import ua.com.serzh.help.CreateEntity;
import ua.com.serzh.utils.Utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Serzh on 11/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ("classpath:json-test-application-context.xml"))
public class UserDaoJsonIntegrationTest {

    private static String testUsersFile = "src/test/resources/json/test-users.json";

    @Autowired
    private UserDaoJSON userDaoJSON;

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
        cleanFile();
        userDaoJSON.cleanUserStore();
    }

    @Test
    public void shouldAutowiredDependencies() {
        assertNotNull(userDaoJSON);
        assertNotNull(userStore);
        assertNotNull(utils);
        assertNotNull(mapper);
    }

    @Test
    public void addUserTest() throws Exception {
        User user = CreateEntity.createUser1();
        userDaoJSON.addUser(user);

        UserStore userStore = (UserStore) mapper.getObjectFromFile(testUsersFile, UserStore.class.getName());
        assertEquals(1, userStore.getCountUsers());
        assertEquals("users", userStore.getName());

        List<User> users = userStore.getUsers();
        User luciano = users.get(0);
        assertEquals("Luciano", luciano.getName());
        assertEquals("first", luciano.getPassword());
        assertEquals("1", luciano.getUserId().toString());
    }

    @Test
    public void addTwoUsersTest() throws Exception {
        User user = CreateEntity.createUser1();
        User user2 = CreateEntity.createUser2();
        userDaoJSON.addUser(user);
        userDaoJSON.addUser(user2);

        UserStore userStore = (UserStore) mapper.getObjectFromFile(testUsersFile, UserStore.class.getName());
        assertEquals(2, userStore.getCountUsers());

        List<User> users = userStore.getUsers();
        User luciano = users.get(0);
        assertEquals("Luciano", luciano.getName());
        assertEquals("first", luciano.getPassword());
        assertEquals("1", luciano.getUserId().toString());

        User domingo = users.get(1);
        assertEquals("Domingo", domingo.getName());
        assertEquals("second", domingo.getPassword());
        assertEquals("2", domingo.getUserId().toString());
    }

    @Test
    public void searchByNameAndPasswordTest() throws Exception {
        User user = CreateEntity.createUser1();
        userDaoJSON.addUser(user);

        User luciano = userDaoJSON.searchByNameAndPassword("Luciano", "first");
        assertEquals("Luciano", luciano.getName());
        assertEquals("first", luciano.getPassword());
        assertEquals("1", luciano.getUserId().toString());
    }

    @Test
    public void searchByNameTest() throws Exception {
        User user = CreateEntity.createUser1();
        user.setName("Santiago");
        userDaoJSON.addUser(user);

        User luciano = userDaoJSON.searchByName("Santiago");
        assertEquals("Santiago", luciano.getName());
        assertEquals("first", luciano.getPassword());
        assertEquals("1", luciano.getUserId().toString());
    }

    @Test
    public void searchByNameAndPassword() throws Exception {
        User user = CreateEntity.createUser1();
        userDaoJSON.addUser(user);

        User luciano = userDaoJSON.searchByNameAndPassword("Luciano", "first2");
        assertEquals(null, luciano);
    }

    @Test
    public void searchByNameTestNull() throws Exception {
        User user = CreateEntity.createUser1();
        userDaoJSON.addUser(user);

        User luciano = userDaoJSON.searchByName("Luciano2");
        assertEquals(null, luciano);
    }

    private static void cleanFile() {
        try {
            new FileOutputStream(testUsersFile, false).close();
//            new FileWriter(testUsersFile, false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}