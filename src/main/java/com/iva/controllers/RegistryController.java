package com.iva.controllers;

import com.iva.dao.UserDao;
import com.iva.entities.User;
import com.iva.validation.Validation;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistryController extends HttpServlet {
    public RegistryController() {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        User user = null;
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        boolean isValidName = false;
        boolean isValidPassword = false;
        boolean isPasswordsMatch = false;
        if(name != null) {
            user = userDao.searchByName(name);
            isValidName = Validation.validate(name, "\\w{5,}");
        }

        if(password != null) {
            isValidPassword = Validation.validate(password, "[a-zA-Z0-9]{5,}");
        }

        if(password != null && confirmPassword != null) {
            isPasswordsMatch = password.equals(confirmPassword);
        }

        RequestDispatcher view;
        if(user == null && isValidName && isValidPassword && isPasswordsMatch) {
            user = new User(name, password);
            userDao.insertUser(user);
            view = req.getRequestDispatcher("/WEB-INF/jsps/login.jsp");
        } else {
            if(user != null) {
                req.setAttribute("userExist", true);
            }

            req.setAttribute("validUserName", true);
            req.setAttribute("correctPassword", isValidPassword);
            req.setAttribute("passwordsMatch", isPasswordsMatch);
            req.setAttribute("name", name);
            req.setAttribute("confirmPassword", confirmPassword);
            req.setAttribute("password", password);
            view = req.getRequestDispatcher("/WEB-INF/jsps/registry.jsp");
        }

        view.forward(req, resp);
    }
}
