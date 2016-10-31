import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.com.serzh.dao.UserDao;
import ua.com.serzh.dao.UserDaoImpl;
import ua.com.serzh.entities.User;

public class App {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
//        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("webapp/WEB-INF/spring/Spring-Module.xml");
//        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/Spring-Module.xml");
//        ApplicationContext context = new ClassPathXmlApplicationContext("/Spring-Module.xml");
//        ApplicationContext context = new ClassPathXmlApplicationContext("/Users/Serzh/IdeaProjects/Temp/Phonebook/src/main/webapp/WEB-INF/application-context.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext("/dao/application-context.xml");

        UserDaoImpl cust = (UserDaoImpl) context.getBean("userDao");
        System.out.println(cust.getJdbcTemplate());
        System.out.println(cust);

        UserDao userDao = (UserDao) context.getBean("userDao");
        User user = new User("VaniaTest8", "pass4");
        userDao.insertUser(user);

        User searchByNameAndPassword = userDao.searchByNameAndPassword("VaniaTest6", "pass4");
        System.out.println(searchByNameAndPassword.getName());
    }
}