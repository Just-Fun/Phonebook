package ua.com.serzh.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.com.serzh.dao.UserDao;
import ua.com.serzh.entities.User;
import ua.com.serzh.validation.Validation;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistryController extends HttpServlet {

    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    UserDao userDao = (UserDao) context.getBean("userDao");
    public RegistryController() {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        UserDao userDao = new UserDao();
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
            isValidName = Validation.validate(name, "\\w{5,}"); //TODO change on 3?
        }

        // TODO ФИО (минимум 5 символов)

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
