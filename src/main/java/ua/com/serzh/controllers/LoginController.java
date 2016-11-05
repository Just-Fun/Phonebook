package ua.com.serzh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.serzh.dao.ContactDao;
import ua.com.serzh.dao.UserDao;
import ua.com.serzh.entities.User;
import ua.com.serzh.service.ContactManager;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Serzh on 10/25/16.
 */
@Controller
public class LoginController extends HttpServlet {

    private final UserDao userDao;

    private final ContactDao contactDao;

    @Autowired(required = false)
    public LoginController(UserDao userDao, ContactDao contactDao) {
        this.userDao = userDao;
        this.contactDao = contactDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String menu(HttpServletRequest req) throws ServletException, IOException {
        User user = null;
//        resp.setContentType("text/html");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if (name != null && password != null && !name.isEmpty() && !password.isEmpty()) {
            user = userDao.searchByNameAndPassword(name, password);
        }
        if (user != null && "Submit".equals(req.getParameter("Submit"))) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            List contacts = contactDao.allUserContacts(user.getUserId());
            session.setMaxInactiveInterval(300);
            session.setAttribute("contacts", contacts);

            return "redirect:/main";
        } else {
            req.setAttribute("rightInput", false);
            return "login";
        }
    }
}
