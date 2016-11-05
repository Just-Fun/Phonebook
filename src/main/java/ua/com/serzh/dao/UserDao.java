package ua.com.serzh.dao;

import org.springframework.beans.factory.InitializingBean;
import ua.com.serzh.entities.User;

/**
 * Created by Serzh on 10/31/16.
 */
public interface UserDao extends InitializingBean {
    void addUser(User user);

    User searchByNameAndPassword(String name, String password);

    User searchByName(String name);
}
