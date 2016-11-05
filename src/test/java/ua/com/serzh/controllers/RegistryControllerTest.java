package ua.com.serzh.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.serzh.dao.UserDao;
import ua.com.serzh.entities.User;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by Serzh on 10/31/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ("classpath:db-test-application-context.xml"))
public class RegistryControllerTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RegistryController registryController;


    private HttpServletRequest request;

    @Before
    public void init() {
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void shouldAutowiredDependencies() {
        assertNotNull(userDao);
        assertNotNull(registryController);
    }

    @Test
    public void testRegistry() throws Exception {
        String result = registryController.registry();
        assertEquals("registry", result);
    }

    @Test
    public void testRegistrySuccess() throws Exception {
        String name = "Cleopatra";
        String password = "Egypt";
        ArgumentCaptor<User> user = ArgumentCaptor.forClass(User.class);

        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("confirmPassword")).thenReturn(password);
//      the name is available for use
        when(userDao.searchByName(name)).thenReturn(null);

        String result = registryController.registryPost(request);

        verify(userDao).addUser(user.capture());
        // just in case
        verify(userDao).addUser(Mockito.anyObject());

        assertEquals("login", result);

        assertEquals("Cleopatra", user.getValue().getName());
        assertEquals("Egypt", user.getValue().getPassword());
    }

    @Test
    public void testRegistryNameIsUsed() throws Exception {
        String name = "Cleopatra";
        String password = "Egypt";
        User user = new User(name, password);

        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("confirmPassword")).thenReturn(password);
//      the name is available for use
        when(userDao.searchByName(name)).thenReturn(user);

        String result = registryController.registryPost(request);

        verify(request).setAttribute("userExist", true);
        verify(request).setAttribute("validUserName", true);
        verify(request).setAttribute("correctPassword", true);
        verify(request).setAttribute("passwordsMatch", true);
        verify(request).setAttribute("name", name);
        verify(request).setAttribute("password", password);

        assertEquals("registry", result);
    }

    @Test
    public void testRegistryWrongInput() throws Exception {
        String name = "";
        String password = "E";
        String confirmPassword = "Ee";

        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("confirmPassword")).thenReturn(confirmPassword);

        String result = registryController.registryPost(request);

        verify(userDao, never()).searchByName(name);
        verify(request, never()).setAttribute("userExist", true);

        verify(request).setAttribute("validUserName", false);
        verify(request).setAttribute("correctPassword", false);
        verify(request).setAttribute("passwordsMatch", false);
        verify(request).setAttribute("name", name);
        verify(request).setAttribute("password", password);

        assertEquals("registry", result);
    }
}