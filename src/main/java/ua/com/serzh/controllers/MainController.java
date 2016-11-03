package ua.com.serzh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.serzh.dao.ContactDao;
import ua.com.serzh.entities.User;
import ua.com.serzh.service.ContactManager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Serzh on 10/25/16.
 */
@Controller
public class MainController extends HttpServlet {

    private final ContactDao contactDao;
    private ContactManager contactManager;

    @Autowired(required = false)
    public MainController(ContactDao contactDao, ContactManager contactManager) {
        this.contactDao = contactDao;
        this.contactManager = contactManager;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.setAttribute("user", null);
        return "redirect:/login";
    }

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main(HttpServletRequest req, HttpSession session) throws ServletException, IOException {
        User user;
        try {
            user = (User) session.getAttribute("user");
        } catch (NullPointerException e) { // crutch, temp
            return "redirect:/login";
        }

        if (user == null) {
            return "redirect:/login";
        }

        if ("New".equals(req.getParameter("button"))) {
            session.setAttribute("add", true);
        } else if ("Edit".equals(req.getParameter("button"))) {
            session.setAttribute("edit", true);
        } else if ("Delete".equals(req.getParameter("button"))) {
            session.setAttribute("delete", true);
        }

        Integer pageNumber = (Integer) session.getAttribute("pageNumber");
        contactManager.action(req, contactDao, session, user);

        return "main";
    }

    @RequestMapping(value = "main", method = RequestMethod.POST)
    public void mainPost(HttpServletRequest req, HttpSession session) throws ServletException, IOException {
        main(req, session);
    }
}
