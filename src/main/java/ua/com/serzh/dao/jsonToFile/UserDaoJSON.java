package ua.com.serzh.dao.jsonToFile;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.serzh.dao.UserDao;
import ua.com.serzh.entities.User;
import ua.com.serzh.utils.Utils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Serzh on 11/4/16.
 */
public class UserDaoJSON implements UserDao {
   /* public UserDaoJSON() {
    }*/

    private String pathUsersFile;
    private String fileName = "path_users.json";

    private Utils utils;
    private MapperObjectJson mapper;

    private UserStore userStore;

    @Autowired
    public UserDaoJSON(Utils utils, MapperObjectJson mapper) throws IOException {
        this.utils = utils;
        this.mapper = mapper;
    }

    @PostConstruct
    private void init() throws IOException {

        pathUsersFile = utils.getProperties(fileName);

        String mappingClassName = UserStore.class.getName();
        userStore = (UserStore) mapper.getObjectFromFile(pathUsersFile, mappingClassName);

        if (userStore == null) {
            userStore = new UserStore();
        }
    }

    @Override
    public void addUser(User user) {
        userStore.addUser(user);
        mapper.writeJsonToFile(userStore, pathUsersFile);
    }

    @Override
    public User searchByNameAndPassword(String name, String password) {
        return userStore.searchByNameAndPassword(name, password);
    }

    @Override
    public User searchByName(String name) {
        return userStore.searchByName(name);
    }

    // for tests
    public void cleanUserStore() {
        userStore.setCountUsers(0);
        userStore.setUsers(new ArrayList<>());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}