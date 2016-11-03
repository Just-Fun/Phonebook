package ua.com.serzh.service;

import ua.com.serzh.dao.ContactDao;
import ua.com.serzh.entities.Contact;
import ua.com.serzh.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serzh on 10/25/16.
 */
public class ContactManager {
    public ContactManager() {
    }

    public void action(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user) {
        if (session.getAttribute("add") == null) {
            session.setAttribute("add", false);
        }

        if (session.getAttribute("edit") == null) {
            session.setAttribute("edit", false);
        }

        if (session.getAttribute("add").equals(true)) {
            //TODO autowired
            boolean add = new AddingContacts().run(req, contactDao, session, user);
            if (add) {
                showContacts(contactDao, user, session);
            }

        } else if (session.getAttribute("edit").equals(true)) {
            //TODO autowired
            new EditingContact().editContact(req, contactDao, session, user);
        } else if ("Delete".equals(req.getParameter("button"))) {
            deleteContact(req, contactDao, session, user);
        } else {
            searchContact(req, contactDao, session, user);
        }
    }

    public void showContacts(ContactDao contactDao, User user, HttpSession session) {
        Boolean listChanged = (Boolean) session.getAttribute("listChanged");
        if (listChanged == null) {
            listChanged = false;
        }

        List contacts = (List) session.getAttribute("contacts");
        if (contacts == null || listChanged) {
            contacts = contactDao.allUserContacts(user.getUserId());
            session.setAttribute("contacts", contacts);
        }
        session.setAttribute("listChanged", false);
    }

    /*private void editContact(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user) {
        String subscriberName = req.getParameter("editName");
        String mobileNumber = req.getParameter("mobileNumber");
        String contactId = req.getParameter("select");
        Contact contact = (Contact) session.getAttribute("contact");
        if (contactId != null) {
            contact = contactDao.searchContactById(Integer.parseInt(contactId));
            session.setAttribute("contact", contact);
        }

        if ("Ok".equals(req.getParameter("ok"))) {
            if (contact != null) {
                contact.setName(subscriberName);
                contact.setMobileNumber(mobileNumber);
                contactDao.updateContact(contact);

                session.setAttribute("edit", false);
                session.setAttribute("listChanged", true);
                showContacts(contactDao, user, session);
            }
            // TODO moved to begin
        } else if ("Cancel".equals(req.getParameter("cancel"))) {
            session.setAttribute("edit", false);
        }
    }*/

    private void deleteContact(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user) {
        String contactId = req.getParameter("select");
        if (contactId != null) {
            Contact contact = contactDao.searchContactById(Integer.parseInt(contactId));

            contactDao.deleteContact(contact);

            req.setAttribute("info", true);
            String textInfo = String.format("'%s' was deleted from the list of contacts", contact.getName());
            req.setAttribute("textInfo", textInfo);

            session.setAttribute("listChanged", true);
            showContacts(contactDao, user, session);
        }
    }

    private void searchContact(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user) {
        String searchQuery = req.getParameter("searchQuery");
        if ("Search".equals(req.getParameter("searchButton")) && searchQuery != null && user != null) {
            List contacts;
            if (searchQuery.isEmpty()) {
                contacts = new ArrayList();
            } else {
                contacts = contactDao.searchContactByAnyField(searchQuery, user.getUserId());
            }
            session.setAttribute("contacts", contacts);
        }

        if ("All Contacts".equals(req.getParameter("allContacts")) && user != null) {
            session.setAttribute("contacts", null);
            showContacts(contactDao, user, session);
        }
    }

    // TODO remove pages? show all contacts
    public void pagination(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user, int pageNumber) {
        if ("Next".equals(req.getParameter("pageButton"))) {
            ++pageNumber;
        } else if ("Prev".equals(req.getParameter("pageButton"))) {
            --pageNumber;
        }

        session.setAttribute("pageNumber", pageNumber);
        showContacts(contactDao, user, session);
    }

    public int getAmountOfContacts(ContactDao contactDao, User user) {
        return contactDao.allUserContacts(user.getUserId()).size();
    }
}