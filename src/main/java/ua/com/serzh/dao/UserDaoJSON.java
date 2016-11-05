package ua.com.serzh.dao;

import ua.com.serzh.entities.User;
import ua.com.serzh.utils.Utils;

import java.io.IOException;

import static ua.com.serzh.dao.MapperObjectJson.writeJsonToFile;

/**
 * Created by Serzh on 11/4/16.
 */
public class UserDaoJSON implements UserDao {

    String pathName = Utils.getProperties().getProperty("path_users.json");

    UserStore userStore;

    public UserDaoJSON() throws IOException {
        String mappingClassName = UserStore.class.getName();

        userStore = (UserStore) MapperObjectJson.getObjectFromFile(pathName, mappingClassName);

        if (userStore == null) {
            userStore = new UserStore();
        }
    }

    @Override
    public void addUser(User user) {
        userStore.addUser(user);
        writeJsonToFile(userStore, pathName);
    }

    @Override
    public User searchByNameAndPassword(String name, String password) {
        return userStore.searchByNameAndPassword(name, password);
    }

    @Override
    public User searchByName(String name) {
        return userStore.searchByName(name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
