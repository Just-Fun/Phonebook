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

/**
 * Created by Serzh on 10/25/16.
 */
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
        boolean isValidName = false;
        boolean isValidPassword = false;
        boolean isPasswordsMatch = false;

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        boolean nameEmpty = name.isEmpty();
        boolean passwordEmpty = password.isEmpty();
        boolean confirmPasswordEmpty = confirmPassword.isEmpty();

        if (!nameEmpty) {
            isValidName = Validation.validate(name, Validation.getThreeLetters());
        }

        if (isValidName) {
            user = userDao.searchByName(name);
        }

        if (!passwordEmpty) {
            isValidPassword = Validation.validate(password, Validation.getFiveLettersOrDigits());
        }

        if (!passwordEmpty && !confirmPasswordEmpty) {
            isPasswordsMatch = password.equals(confirmPassword);
        }

        if (user == null && isValidName && isValidPassword && isPasswordsMatch) {
            user = new User(name, password);
            userDao.addUser(user);
            return "login";
        } else {
            if (user != null) {
                req.setAttribute("userExist", true);
            }
            setAttibute(req, isValidName, isValidPassword, isPasswordsMatch, name, password,
                    nameEmpty, passwordEmpty, confirmPasswordEmpty);
            return "registry";
        }
    }

    private void setAttibute(HttpServletRequest req, boolean isValidName, boolean isValidPassword, boolean isPasswordsMatch, String name, String password, boolean nameEmpty, boolean passwordEmpty, boolean confirmPasswordEmpty) {
        req.setAttribute("emptyUserName", nameEmpty);
        req.setAttribute("validUserName", isValidName);

        req.setAttribute("emptyPassword", passwordEmpty);
        req.setAttribute("correctPassword", isValidPassword);

        req.setAttribute("emptyConfirmPassword", confirmPasswordEmpty);
        req.setAttribute("passwordsMatch", isPasswordsMatch);

        req.setAttribute("name", name);
        req.setAttribute("password", password);
    }
}