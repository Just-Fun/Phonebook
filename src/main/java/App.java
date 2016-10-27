import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.com.serzh.dao.UserDao;
import ua.com.serzh.entities.User;

public class App {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

        UserDao cust = (UserDao) context.getBean("userDao");
        System.out.println(cust.getJdbcTemplate());
        System.out.println(cust);

        UserDao userDao = (UserDao) context.getBean("userDao");
        User user = new User("VaniaTest2", "pass1");
        userDao.insertUser(user);

        User searchByNameAndPassword = userDao.searchByNameAndPassword("VaniaTest2", "pass1");
        System.out.println(searchByNameAndPassword.getName());
    }
}