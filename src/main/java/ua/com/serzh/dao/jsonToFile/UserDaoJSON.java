package ua.com.serzh.dao.jsonToFile;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.serzh.dao.UserDao;
import ua.com.serzh.entities.User;
import ua.com.serzh.utils.Utils;

import java.io.IOException;
import java.util.Properties;

import static ua.com.serzh.dao.jsonToFile.MapperObjectJson.*;
import static ua.com.serzh.dao.jsonToFile.MapperObjectJson.writeJsonToFile;

/**
 * Created by Serzh on 11/4/16.
 */
public class UserDaoJSON implements UserDao {
  /*  public UserDaoJSON() {
    }*/

    private String pathName;

    private Utils utils;
    private  MapperObjectJson mapper;

    private UserStore userStore;

    @Autowired/*(required = false)*/
    public UserDaoJSON(Utils utils, MapperObjectJson mapper) throws IOException {
        this.utils = utils;
        this.mapper = mapper;

        String mappingClassName = UserStore.class.getName();

        Properties properties = utils.getProperties();
        pathName = properties.getProperty("path_users.json");

        userStore = (UserStore) mapper.getObjectFromFile(pathName, mappingClassName);

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
