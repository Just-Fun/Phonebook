package ua.com.serzh.help;

import ua.com.serzh.entities.Contact;
import ua.com.serzh.entities.User;

/**
 * Created by Serzh on 11/4/16.
 */
public class CreateEntity {

    public static User createUser1() {
        User user = new User("Luciano", "first");
        user.setUserId(1);
        return user;
    }

    public static User createUser2() {
        User user = new User("Domingo", "second");
        user.setUserId(2);
        return user;
    }

    public static Contact createContact1(User user) {

        String surname = "Joli";
        String name = "Angelina";
        String patronymic = "Petrovna";
        String mobileNumber = "+380664563345";
        String homePhone = "+380444563345";
        String address = "Kyiv, Sviatkova 15";
        String email = "joli@.gmail.com";

        Contact contact = new Contact(
                surname, name, patronymic, mobileNumber, homePhone, address, email, user.getUserId());

        return contact;
    }

    public static Contact createContact2(User user) {

        String surname = "Joli2";
        String name = "Angelina2";
        String patronymic = "Petrovna2";
        String mobileNumber = "+380664563346";
        String homePhone = "+380444563346";
        String address = "Kyiv, Sviatkova 16";
        String email = "joli2@.gmail.com";

        Contact contact = new Contact(
                surname, name, patronymic, mobileNumber, homePhone, address, email, user.getUserId());

        return contact;
    }
}
