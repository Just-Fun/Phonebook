package ua.com.serzh.controllers;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.serzh.dao.ContactDao;
import ua.com.serzh.dao.UserDao;
import ua.com.serzh.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Serzh on 10/31/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ("classpath:db-test-application-context.xml"))
public class LoginControllerTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ContactDao contactDao;

    @Autowired
    private LoginController loginController;

    private static HttpServletRequest request;
    private static HttpSession session;

    @BeforeClass
    public static void init() {
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void shouldAutowiredDependencies() {
        assertNotNull(userDao);
        assertNotNull(contactDao);
        assertNotNull(loginController);
    }

    @Test
    public void testMainUserDaoSearchByNameAndPassword() throws Exception {
        String name = "Vasiliy";
        String password = "password1";

        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("password")).thenReturn(password);
//        when(userDao.searchByNameAndPassword(name, password)).thenReturn(user);
        loginController.menu(request);
        verify(userDao, atLeast(1)).searchByNameAndPassword(name, password);
    }

    @Test
    public void testMainContactDaoAllUserContacts() throws Exception {
        String name = "Vasiliy";
        String password = "password1";

        User user = new User(name, password);
        user.setUserId(1);

        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("Submit")).thenReturn("Submit");
        when(request.getSession()).thenReturn(session);

        when(userDao.searchByNameAndPassword(name, password)).thenReturn(user);
        String result = loginController.menu(request);
        verify(contactDao, atLeastOnce()).allUserContacts(user.getUserId());
        assertEquals("redirect:/main", result);
    }

    @Test
    public void testIfUserNull() throws Exception {
        when(request.getParameter("name")).thenReturn(null);
        when(request.getParameter("password")).thenReturn(null);

        String result = loginController.menu(request);
        verify(request).setAttribute("rightInput", false);
        assertEquals("login", result);
    }

    @Test
    public void testLogin() throws Exception {
        String result = loginController.login();
        assertEquals("login", result);
    }

    @Test
    public void testMain() throws Exception {
        String result = loginController.main();
        assertEquals("redirect:/login", result);
    }
}