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

@Controller
public class MainController extends HttpServlet {

    private final ContactDao contactDao;
//    ContactManager contactManager;

    @Autowired(required = false)
    public MainController(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest req) {
        if ("logout".equals(req.getParameter("button"))) {
            HttpSession session = req.getSession(false);
            session.invalidate();
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main(HttpServletRequest req) throws ServletException, IOException {

        if (sessionIsInvalid(req)) {
            return "redirect:/login";
        }
        HttpSession session = req.getSession(false);
//        resp.setContentType("text/html");
        User user;
//        try {
        user = (User) session.getAttribute("user");
//        } catch (NullPointerException e) {
//            return "redirect:/login";
//        }

        ContactManager contactManager = new ContactManager(); // TODO bean

        if ("New".equals(req.getParameter("button"))) {
            session.setAttribute("add", true);
        } else if ("Edit".equals(req.getParameter("button"))) {
            session.setAttribute("edit", true);
        }

        Integer pageNumber = (Integer) session.getAttribute("pageNumber");
        contactManager.action(req, contactDao, session, user);
        contactManager.pagination(req, contactDao, session, user, pageNumber);
        if ("logout".equals(req.getParameter("button"))) {
            session.invalidate();
            return "login";
        } else {
            return "main";
        }
    }

    @RequestMapping(value = "main", method = RequestMethod.POST)
    public void mainPost(HttpServletRequest req) throws ServletException, IOException {
       /* if (sessionIsInvalid(req)) {
            return "redirect:/login";
        }*/
        main(req);
    }

    private boolean sessionIsInvalid(HttpServletRequest req) {
        return req.getSession(false) == null || !req.isRequestedSessionIdValid();
    }

}
