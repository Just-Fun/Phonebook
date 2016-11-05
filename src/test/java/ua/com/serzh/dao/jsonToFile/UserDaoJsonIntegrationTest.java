package ua.com.serzh.dao.jsonToFile;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.serzh.entities.User;
import ua.com.serzh.help.CreateEntity;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Serzh on 11/5/16.
 */
@Ignore
public class UserDaoJsonIntegrationTest {



    @BeforeClass
    public static void init() throws IOException {

    }


    @Test
    public void addUser() throws Exception {
//        UserDaoJSON userDaoJSON = new UserDaoJSON();
//        User userNew = CreateEntity.createUser1();
//        userNew.setName("ShwartzFIve");
//
//        userDaoJSON.addUser(userNew);
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