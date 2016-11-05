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
    private Contact contact;

    public ContactManager() {
    }

    public void action(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user) {
        if (session.getAttribute("add") == null) {
            session.setAttribute("add", false);
        }

        if (session.getAttribute("edit") == null) {
            session.setAttribute("edit", false);
        }

        if (session.getAttribute("delete") == null) {
            session.setAttribute("delete", false);
        }


        if (session.getAttribute("add").equals(true)) {
            if ("Cancel".equals(req.getParameter("cancel"))) {
                session.setAttribute("add", false);
            } else {
                if ("Ok".equals(req.getParameter("ok"))) {
                    //TODO autowired
                    boolean add = new ContactEditor().addContact(req, contactDao, session, user);
                    if (add) {
                        session.setAttribute("listChanged", true);
                        showContacts(contactDao, user, session);
                    }
                }
            }

        } else if (session.getAttribute("edit").equals(true)) {
            if ("Cancel".equals(req.getParameter("cancel"))) {
                session.setAttribute("edit", false);
            } else {

                //TODO autowired
                boolean edit = new ContactEditor().editContact(req, session, contactDao);
                if (edit) {
                    session.setAttribute("listChanged", true);
                    showContacts(contactDao, user, session);
                }
            }

        } else if (session.getAttribute("delete").equals(true)) {
            if ("Cancel".equals(req.getParameter("cancel"))) {
                session.setAttribute("delete", false);
            } else {
                boolean delete = deleteContact(req, contactDao, session);
                if (delete) {
                    session.setAttribute("listChanged", true);
                    showContacts(contactDao, user, session);
                    session.setAttribute("delete", false);
                }
            }

        } else {
            searchContact(req, contactDao, session, user);
        }
    }

    private void showContacts(ContactDao contactDao, User user, HttpSession session) {
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

    private boolean deleteContact(HttpServletRequest req, ContactDao contactDao, HttpSession session) {
        String contactId = req.getParameter("select");

        if (contactId != null) {
            contact = contactDao.searchContactById(Integer.parseInt(contactId));
            session.setAttribute("contact", contact);
        } else {
            contact = (Contact) session.getAttribute("contact");
        }

        if ("Ok".equals(req.getParameter("ok"))) {
            contactDao.deleteContact(contact);

            req.setAttribute("info", true);
            String textInfo = String.format("'%s %s' was removed from the list of contacts",
                    contact.getSurname(), contact.getName());
            req.setAttribute("textInfo", textInfo);

            return true;
        }
        return false;
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
}