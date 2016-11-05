package ua.com.serzh.dao.jsonToFile;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.serzh.entities.User;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Serzh on 11/5/16.
 */
@Ignore
public class UserDaoJSONTest {

    private static UserDaoJSON userDao;
    private static UserStore userStore;
    private static User user;
    private static MapperObjectJson mapper;

    @BeforeClass
    public static void init() throws IOException {
//        userDao = new UserDaoJSON();
        userStore = mock(UserStore.class);
        user = mock(User.class);
        mapper = mock(MapperObjectJson.class);

    }

    @Test
    public void shouldAutowiredDependencies() {
        assertNotNull(userDao);
        assertNotNull(userStore);
        assertNotNull(user);
        assertNotNull(mapper);
    }

    @Test
    public void addUser() throws Exception {
//        when(a.method(any(Class.class))).thenReturn(b);
//       userDao.addUser(any(User.class));
//        MapperObjectJson.getObjectFromFile
        when(mapper.getObjectFromFile(any(), any())).thenReturn(userStore);

        userDao.addUser(user);
        verify(userStore).addUser(user);

//        verify(mapper).writeJsonToFile(userStore, any(String.class));
        verify(mapper).writeJsonToFile(userStore, any(String.class));

    }

    @Test
    public void searchByNameAndPassword() throws Exception {

    }

    @Test
    public void searchByName() throws Exception {

    }

    /*public static void main(String[] args) {

      /*  UserDaoJSON userDaoJSON = new UserDaoJSON();

        User userNew = CreateEntity.createUser1();
        userNew.setName("ShwartzFIve");

        userDaoJSON.addUser(userNew);*/

//        System.out.println("id: " + user.getUserId() + ", name: " + user.getName() + ", password: " + user.getPassword());
}