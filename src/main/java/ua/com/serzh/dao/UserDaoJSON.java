package ua.com.serzh.dao;

import ua.com.serzh.entities.User;

import static ua.com.serzh.dao.MapperObjectJson.writeJsonToFile;

/**
 * Created by Serzh on 11/4/16.
 */
public /*abstract*/ class UserDaoJSON implements UserDao {

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    static String pathName = "src/main/resources/users.json";

    UserStore userStore;

    public UserDaoJSON() {
        String mappingClassName = UserStore.class.getName();
        userStore = (UserStore) MapperObjectJson.getObjectFromFile(pathName, userStore, mappingClassName);
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
}
