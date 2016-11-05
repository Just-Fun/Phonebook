package ua.com.serzh.dao;

import ua.com.serzh.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serzh on 11/4/16.
 */
public class UserStore {

    private String name = "users";
    int countUsers;
    private  List<User> users;

    public UserStore() {
        if (users == null) {
            users = new ArrayList<>();
        }
    }

    public int getCountUsers() {
        return countUsers;
    }

    public void setCountUsers(int countUsers) {
        this.countUsers = countUsers;
    }

    public UserStore(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  List<User> getUsers() {
        return users;
    }

    public  void setUsers(List<User> users) {
        this.users = users;
    }

    public List list() {
        return users;
    }

    public User get(Integer id) {

        for (User c : users) {
            if (c.getUserId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public Integer delete(Integer id) {

        for (User user : users) {
            if (user.getUserId().equals(id)) {
                users.remove(user);
                return id;
            }
        }
        return null;
    }

    public User update(Integer id, User userNew) {

        for (User userOld : users) {
            if (userOld.getUserId().equals(id)) {
                userNew.setUserId(userOld.getUserId());
                users.remove(userOld);
                users.add(userNew);
                return userNew;
            }
        }
        return null;
    }

    public void addUser(User user) {
        int id = getCountUsers();
        user.setUserId(++id);
        setCountUsers(id);
        users.add(user);
    }

   public User searchByNameAndPassword(String name, String password){
       for (User user : users) {
           if (user.getName().equals(name) && user.getPassword().equals(password)) {
               return user;
           }
       }
       return null;
   }

    public User searchByName(String name){
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }
}