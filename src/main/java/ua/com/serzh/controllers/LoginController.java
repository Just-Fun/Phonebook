//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ua.com.serzh.controllers;

import ua.com.serzh.dao.ContactDao;
import ua.com.serzh.dao.UserDao;
import ua.com.serzh.entities.User;
import ua.com.serzh.service.ContactManager;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {
    public LoginController() {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        ContactDao contactDao = new ContactDao();
        User user = null;
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if(name != null && password != null && !name.isEmpty() && !password.isEmpty()) {
            user = userDao.searchByNameAndPassword(name, password);
        }

        RequestDispatcher view;
        if(user != null && "Submit".equals(req.getParameter("Submit"))) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            List contacts = contactDao.allUserContacts(user.getUserId().intValue());
            session.setMaxInactiveInterval(300);
            session.setAttribute("contacts", contacts);
            int amountOfContacts = (new ContactManager()).getAmountOfContacts(contactDao, user);
            session.setAttribute("amountOfContacts", Integer.valueOf(amountOfContacts));
            session.setAttribute("pageNumber", Integer.valueOf(1));
            view = req.getRequestDispatcher("/WEB-INF/jsps/main.jsp");
        } else {
            view = req.getRequestDispatcher("/WEB-INF/jsps/registry.jsp");
        }

        view.forward(req, resp);
    }
}
