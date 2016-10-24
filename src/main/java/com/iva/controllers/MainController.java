package com.iva.controllers;

import com.iva.dao.ContactDao;
import com.iva.entities.User;
import com.iva.service.ContactManager;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainController extends HttpServlet {
    public MainController() {
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContactDao contactDao = new ContactDao();
        ContactManager contactManager = new ContactManager();
        resp.setContentType("text/html");
        HttpSession session = req.getSession(false);
        User user = (User)session.getAttribute("user");
        if("New".equals(req.getParameter("button"))) {
            session.setAttribute("add", true);
        } else if("Edit".equals(req.getParameter("button"))) {
            session.setAttribute("edit", true);
        }

        Integer pageNumber = (Integer)session.getAttribute("pageNumber");
        contactManager.action(req, contactDao, session, user);
        contactManager.pagination(req, contactDao, session, user, pageNumber.intValue());
        RequestDispatcher view;
        if("logout".equals(req.getParameter("button"))) {
            session.invalidate();
            view = req.getRequestDispatcher("/WEB-INF/jsps/login.jsp");
        } else {
            view = req.getRequestDispatcher("/WEB-INF/jsps/main.jsp");
        }

        if(view != null) {
            view.forward(req, resp);
        }

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
