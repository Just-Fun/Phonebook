package ua.com.serzh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.serzh.dao.UserDao;
import ua.com.serzh.entities.User;
import ua.com.serzh.validation.Validation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistryController extends HttpServlet {

    private final UserDao userDao;

    @Autowired(required = false)
    public RegistryController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "registry", method = RequestMethod.GET)
    public String registry() {
        return "registry";
    }

    @RequestMapping(value = "registry", method = RequestMethod.POST)
    protected String registryPost(HttpServletRequest req) throws ServletException, IOException {
        User user = null;
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        boolean isValidName = false;
        boolean isValidPassword = false;
        boolean isPasswordsMatch = false;
        // TODO check if != null && !.isEmpty() bought need
        if(name != null && !name.isEmpty()) {
//            user = userDao.searchByName(name);
            isValidName = Validation.validate(name, "[a-zA-Z]{3,}");
        }

        if (isValidName) {
            user = userDao.searchByName(name);
        }

        if(password != null && !password.isEmpty()) {
            isValidPassword = Validation.validate(password, "\\w{5,}");
        }

        if(password != null && !password.isEmpty() && confirmPassword != null && !confirmPassword.isEmpty()) {
            isPasswordsMatch = password.equals(confirmPassword);
        }

        if(user == null && isValidName && isValidPassword && isPasswordsMatch) {
            user = new User(name, password);
            userDao.insertUser(user);
            return "login";
        } else {
            if(user != null) {
                req.setAttribute("userExist", true);
            }
            req.setAttribute("validUserName", isValidName);
            req.setAttribute("correctPassword", isValidPassword);
            req.setAttribute("passwordsMatch", isPasswordsMatch);
            req.setAttribute("name", name);
            req.setAttribute("confirmPassword", confirmPassword);// TODO is this need?
            req.setAttribute("password", password);
         return "registry";
        }
    }
}
