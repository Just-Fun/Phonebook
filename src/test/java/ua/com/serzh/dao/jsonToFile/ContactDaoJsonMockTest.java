package ua.com.serzh.dao.jsonToFile;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.serzh.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Serzh on 11/10/16.
 */
@Ignore
public class ContactDaoJsonMockTest {

    private Utils utils;
    private MapperObjectJson mapper;
    private ContactStore contactStore;

    ContactDaoJSON contactDaoJSON;

    @Before
    public void setUp() throws Exception {
        utils = mock(Utils.class);
        mapper = mock(MapperObjectJson.class);
        contactStore = mock(ContactStore.class);

        contactDaoJSON = new ContactDaoJSON(utils, mapper);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void searchContactById() throws Exception {

    }

    @Test
    public void insertContact() throws Exception {

    }

    @Test
    public void allUserContacts() throws Exception {

    }

    @Test
    public void updateContact() throws Exception {

    }

    @Test
    public void deleteContact() throws Exception {

    }

    @Test
    public void searchContactByAnyField() throws Exception {

    }

    @Test
    public void afterPropertiesSet() throws Exception {

    }

    @Test
    public void cleanContactStore() throws Exception {

    }

}