import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.com.serzh.dao.UserDao;
import ua.com.serzh.dao.UserDaoImpl;
import ua.com.serzh.entities.User;

public class App {
    public static void main(String[] args) {
       /* ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/application-context.xml");
//        ApplicationContext context = new ClassPathXmlApplicationContext("webapp/WEB-INF/application-context.xml");

        UserDaoImpl cust = (UserDaoImpl) context.getBean("userDao");
        System.out.println(cust.getJdbcTemplate());
        System.out.println(cust);

        UserDao userDao = (UserDao) context.getBean("userDao");
        User user = new User("VaniaTest8", "pass4");
        userDao.insertUser(user);

        User searchByNameAndPassword = userDao.searchByNameAndPassword("VaniaTest6", "pass4");
        System.out.println(searchByNameAndPassword.getName());*/
    }
}