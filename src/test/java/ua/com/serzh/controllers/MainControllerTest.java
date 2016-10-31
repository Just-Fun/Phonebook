package ua.com.serzh.controllers;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.serzh.dao.ContactDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Serzh on 10/31/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ("classpath:test-application-context.xml"))
public class MainControllerTest {

    @Autowired
    private ContactDao contactDao;

    @Autowired
    private MainController mainController;

    private static HttpServletRequest request;
    private static HttpSession session;

    @BeforeClass
    public static void init() {
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void shouldAutowiredDependencies() {
        assertNotNull(contactDao);
        assertNotNull(mainController);
    }

    @Test
    public void testLogOutSessionInvald() throws Exception {
        when(request.getParameter("button")).thenReturn("logout");
        when(request.getSession(false)).thenReturn(session);

        String result = mainController.logout(request);

        verify(session).invalidate();
        assertEquals("redirect:/login", result);
    }

    @Test
    public void testLogOutSessionNotInvald() throws Exception {
        when(request.getParameter("button")).thenReturn("someAnother");

        String result = mainController.logout(request);

        verify(session, never()).invalidate();
        assertEquals("redirect:/login", result);
    }

    @Test
    public void testMainSessionInvalid() throws Exception {
//        session.invalidate();
        when(request.getSession(false)).thenReturn(session);
        String result = mainController.main(request);
        assertEquals(result, "redirect:/login");

    }

    @Test
    public void testMain() throws Exception {
        session.setAttribute("add", true);
        when(request.getSession(false)).thenReturn(session);
        when(request.isRequestedSessionIdValid()).thenReturn(true);
        String result = mainController.main(request);
        verify(session, atLeastOnce()).getAttribute("user");

    }

    @Test
    public void mainPost() throws Exception {
        MainController controller = Mockito.spy(mainController);

        controller.mainPost(request);

        verify(controller).main(request);
    }
}