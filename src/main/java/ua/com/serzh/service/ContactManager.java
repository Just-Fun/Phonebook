package ua.com.serzh.service;

import ua.com.serzh.dao.ContactDao;
import ua.com.serzh.entities.Contact;
import ua.com.serzh.entities.User;
import ua.com.serzh.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
            addContact(req, contactDao, session, user);
        } else if (session.getAttribute("edit").equals(true)) {
            editContact(req, contactDao, session, user);
        } else if ("Delete".equals(req.getParameter("button"))) {
            deleteContact(req, contactDao, session, user);
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

    // TODO make GET & POST?
    private void addContact(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user) {
        if ("Cancel".equals(req.getParameter("cancel"))) {
            session.setAttribute("add", false);
            return;
        }

        String subscriberName = req.getParameter("newSubscriberName");
        if (subscriberName == null) return;

        String mobileNumber = req.getParameter("mobileNumber");

//        if (subscriberName != null) { // TODO temp
        boolean validSubscriberName = false;
        boolean validMobileNumber = false;

        boolean subscriberNameEmpty = subscriberName.isEmpty();
        boolean mobileNumberEmpty = mobileNumber.isEmpty();

        if (!subscriberNameEmpty) {
            validSubscriberName = Validation.validate(subscriberName, Validation.getFourLetters());
        }

        if (!mobileNumberEmpty) {
            validMobileNumber = Validation.validate(mobileNumber, Validation.getPhoneRegex());
        }

        if ("Ok".equals(req.getParameter("ok"))) {

            if (validMobileNumber && validSubscriberName) {
                Contact contact = new Contact(subscriberName, mobileNumber, user.getUserId());
                contactDao.insertContact(contact);

                req.setAttribute("validMobileNumber", true); // TODO don't need?
                session.setAttribute("add", false);
                session.setAttribute("listChanged", true);

                req.setAttribute("info", true);
                String textInfo = subscriberName + " was added to the list of contacts";
                req.setAttribute("textInfo", textInfo);

                showContacts(contactDao, user, session);
            } else {
                setNameAttribute(req, subscriberName, validSubscriberName, subscriberNameEmpty);
                setMobilPhoneAttribute(req, mobileNumber, validMobileNumber, mobileNumberEmpty);

                session.setAttribute("add", true);
            }
       /* } else if ("Cancel".equals(req.getParameter("cancel"))) {

            session.setAttribute("add", false);
        }*/
        }
    }

    private void setMobilPhoneAttribute(HttpServletRequest req, String mobileNumber, boolean validMobileNumber, boolean mobileNumberEmpty) {
        req.setAttribute("mobileNumberEmpty", mobileNumberEmpty);
        req.setAttribute("validMobileNumber", validMobileNumber);
        req.setAttribute("mobileNumber", mobileNumber);
    }

    private void setNameAttribute(HttpServletRequest req, String subscriberName, boolean validSubscriberName, boolean subscriberNameEmpty) {
        req.setAttribute("emptySubscriberName", subscriberNameEmpty);
        req.setAttribute("validSubscriberName", validSubscriberName);
        req.setAttribute("newSubscriberName", subscriberName);
    }

    private void editContact(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user) {
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
    }

    private void deleteContact(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user) {
        String contactId = req.getParameter("select");
        if (contactId != null) {
            Contact contact = contactDao.searchContactById(Integer.parseInt(contactId));

            contactDao.deleteContact(contact);

            req.setAttribute("info", true);
            String textInfo = contact.getName() + " was deleted from the list of contacts";
            req.setAttribute("textInfo", textInfo);

            session.setAttribute("listChanged", true);
            showContacts(contactDao, user, session);
        }
    }

    private void searchContact(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user) {
        String subscriberName = req.getParameter("subscriberName");
        if ("Search".equals(req.getParameter("searchButton")) && subscriberName != null && user != null) {
            List contacts = contactDao.searchContactByName(subscriberName, user.getUserId());
            session.setAttribute("contacts", contacts);
        }

        if ("All Contacts".equals(req.getParameter("allContacts")) && user != null) {
            session.setAttribute("contacts", null);
            showContacts(contactDao, user, session);
        }
    }

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