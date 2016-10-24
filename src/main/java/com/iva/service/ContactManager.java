package com.iva.service;

import com.iva.dao.ContactDao;
import com.iva.entities.Contact;
import com.iva.entities.User;
import com.iva.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ContactManager {
    public ContactManager() {
    }

    public void action(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user) {
        if(session.getAttribute("add") == null) {
            session.setAttribute("add", false);
        }

        if(session.getAttribute("edit") == null) {
            session.setAttribute("edit", false);
        }

        if(session.getAttribute("add").equals(true)) {
            this.addContact(req, contactDao, session, user);
        } else if(session.getAttribute("edit").equals(true)) {
            this.editContact(req, contactDao, session, user);
        } else if("Delete".equals(req.getParameter("button"))) {
            this.deleteContact(req, contactDao, session, user);
        } else {
            this.searchContact(req, contactDao, session, user);
        }

    }

    private void showContacts(ContactDao contactDao, User user, HttpSession session, HttpServletRequest req) {
        List contacts = (List)session.getAttribute("contacts");
        if(contacts == null) {
            contacts = contactDao.allUserContacts(user.getUserId());
            session.setAttribute("contacts", contacts);
        }

    }

    private void addContact(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user) {
        String subscriberName = req.getParameter("newSubscriberName");
        String mobileNumber = req.getParameter("mobileNumber");
        Contact contact = new Contact(subscriberName, mobileNumber, user.getUserId());
        boolean isValidatePhone = false;
        if(mobileNumber != null) {
            isValidatePhone = Validation.validate(mobileNumber, "[+]\\d{12}");
        }

        if("Ok".equals(req.getParameter("ok")) && isValidatePhone) {
            contact.setName(subscriberName);
            contactDao.insertContact(contact);
            req.setAttribute("validPhone", true);
            session.setAttribute("add", false);
            this.showContacts(contactDao, user, session, req);
        } else if("Cancel".equals(req.getParameter("cancel"))) {
            session.setAttribute("add", false);
        }

    }

    private void editContact(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user) {
        String subscriberName = req.getParameter("editName");
        String mobileNumber = req.getParameter("mobileNumber");
        String contactId = req.getParameter("select");
        Contact contact = (Contact)session.getAttribute("contact");
        if(contactId != null) {
            contact = contactDao.searchContactById(Integer.parseInt(contactId));
            session.setAttribute("contact", contact);
        }

        if("Ok".equals(req.getParameter("ok"))) {
            if(contact != null) {
                contact.setName(subscriberName);
                contact.setMobileNumber(mobileNumber);
                contactDao.updateContact(contact);
                session.setAttribute("edit", false);
                this.showContacts(contactDao, user, session, req);
            }
        } else if("Cancel".equals(req.getParameter("cancel"))) {
            session.setAttribute("edit", false);
        }

    }

    private void deleteContact(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user) {
        String contactId = req.getParameter("select");
        if(contactId != null) {
            Contact contact = contactDao.searchContactById(Integer.parseInt(contactId));
            contactDao.deleteContact(contact);
            this.showContacts(contactDao, user, session, req);
        }

    }

    private void searchContact(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user) {
        String subscriberName = req.getParameter("subscriberName");
        if("Search".equals(req.getParameter("searchButton")) && subscriberName != null && user != null) {
            List contacts = contactDao.searchContactByName(subscriberName, user.getUserId().intValue());
            session.setAttribute("contacts", contacts);
        }

        if("All Contacts".equals(req.getParameter("allContacts")) && user != null) {
            session.setAttribute("contacts", (Object)null);
            this.showContacts(contactDao, user, session, req);
        }

    }

    public void pagination(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user, int pageNumber) {
        if("Next".equals(req.getParameter("pageButton"))) {
            ++pageNumber;
        } else if("Prev".equals(req.getParameter("pageButton"))) {
            --pageNumber;
        }

        session.setAttribute("pageNumber", Integer.valueOf(pageNumber));
        this.showContacts(contactDao, user, session, req);
    }

    public int getAmountOfContacts(ContactDao contactDao, User user) {
        return contactDao.allUserContacts(user.getUserId().intValue()).size();
    }
}
