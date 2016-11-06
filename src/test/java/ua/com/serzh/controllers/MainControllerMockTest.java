package ua.com.serzh.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.serzh.dao.ContactDao;
import ua.com.serzh.entities.User;
import ua.com.serzh.service.ContactManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Serzh on 10/31/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ("classpath:db-test-application-context.xml"))
public class MainControllerMockTest {

    @Autowired
    private ContactDao contactDao;

    @Autowired
    private MainController mainController;

    @Autowired
    private ContactManager contactManager;

    private HttpServletRequest request;
    private HttpSession session;

    @Before
    public void init() {
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void shouldAutowiredDependencies() {
        assertNotNull(contactDao);
        assertNotNull(mainController);
        assertNotNull(contactManager);
    }

    @Test
    public void testLogout() throws Exception {
        String result = mainController.logout(session);
        verify(session).setAttribute("user", null);
        assertEquals(result, "redirect:/login");
    }

    @Test
    public void testMainRedirectLogin() throws Exception {
        when(session.getAttribute("user")).thenReturn(null);
        String result = mainController.main(request, session);
        assertEquals(result, "redirect:/login");
    }

    @Test
    public void testMain() throws Exception {

        User user = setStuff();
        session.setAttribute("add", true);

        String result = mainController.main(request, session);

        verify(session, atLeastOnce()).getAttribute("user");
        verify(contactManager).action(request, contactDao, session, user);

        assertEquals(result, "main");
    }

    @Test
    public void testMainNew() throws Exception {
        setStuff();
        when(request.getParameter("button")).thenReturn("New");

        mainController.main(request, session);
        verify(session, atLeastOnce()).setAttribute("add", true);
    }

    @Test
    public void testMainEdit() throws Exception {
        setStuff();
        when(request.getParameter("button")).thenReturn("Edit");

        mainController.main(request, session);

        verify(session).setAttribute("edit", true);
    }

    @Test
    public void mainPost() throws Exception {
        MainController controller = Mockito.spy(mainController);

        controller.mainPost(request, session);

        verify(controller).main(request, session);
    }

    @Test(expected = NullPointerException.class)
    public void testMainException() throws Exception {
        session = null;
        when(session.getAttribute("user")).thenThrow(new NullPointerException());

        String result = mainController.main(request, session);

        assertEquals(result, "redirect:/login");
    }

    private User setStuff() {
        User user = createUser();
        Integer pageNumber = 1;

        when(session.getAttribute("user")).thenReturn(user);
        when(session.getAttribute("pageNumber")).thenReturn(pageNumber);

        return user;
    }

    private User createUser() {
        String name = "Some Guy";
        String password = "password1";
        User user = new User(name, password);
        user.setUserId(1);
        return user;
    }
}