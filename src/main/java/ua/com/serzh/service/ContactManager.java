package ua.com.serzh.service;

import ua.com.serzh.dao.ContactDao;
import ua.com.serzh.entities.Contact;
import ua.com.serzh.entities.User;
import ua.com.serzh.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

        String name = req.getParameter("newSubscriberName");
        if (name == null) return;

        String surname = req.getParameter("surname");
        String mobileNumber = req.getParameter("mobileNumber");
        String patronymic = req.getParameter("patronymic");
        String homePhone = req.getParameter("homePhone");
        String address = req.getParameter("address");
        String email = req.getParameter("email");

//        if (name != null) { // TODO temp
        boolean validSurname = false;
        boolean validName = false;
        boolean validPatronymic = false;
        boolean validMobileNumber = false;
        boolean validHomePhone = true; // if empty - don't validate
        boolean validEmail = true; // the same

        boolean surnameEmpty = surname.isEmpty();
        boolean nameEmpty = name.isEmpty();
        boolean patronymicEmpty = name.isEmpty();
        boolean mobileNumberEmpty = mobileNumber.isEmpty();

        if (!surnameEmpty) {
            validSurname = Validation.validate(surname, Validation.getFourLettersPattern());
        }

        if (!nameEmpty) {
            validName = Validation.validate(name, Validation.getFourLettersPattern());
        }

        if (!patronymicEmpty) {
            validPatronymic = Validation.validate(patronymic, Validation.getFourLettersPattern());
        }

        if (!mobileNumberEmpty) {
            validMobileNumber = Validation.validate(mobileNumber, Validation.getPhoneRegex());
        }

        if (!homePhone.isEmpty()) {
            validHomePhone = Validation.validate(homePhone, Validation.getPhoneRegex());
        }

        if (!email.isEmpty()) {
            validEmail = Validation.validate(email, Validation.getEmailPattern());
        }

        if ("Ok".equals(req.getParameter("ok"))) {

            if (validSurname && validName && validPatronymic && validMobileNumber & validHomePhone & validEmail) {
//                Contact contact = new Contact(name, mobileNumber, user.getUserId());
                Contact contact = new Contact(name,  surname,  patronymic,  mobileNumber,
                         homePhone,  address,  email, user.getUserId());
                contactDao.insertContact(contact);

//                req.setAttribute("validMobileNumber", true); // TODO don't need?
                session.setAttribute("add", false);
                session.setAttribute("listChanged", true);

                req.setAttribute("info", true);
                String textInfo = surname + " " + name + " was added to the list of contacts";
                req.setAttribute("textInfo", textInfo);

                showContacts(contactDao, user, session);
            } else {
                setSurnameAttribute(req, surname, validSurname, surnameEmpty);
                setNameAttribute(req, name, validName, nameEmpty);
                setPatronymicAttribute(req, patronymic, validPatronymic, patronymicEmpty);
                setMobilPhoneAttribute(req, mobileNumber, validMobileNumber, mobileNumberEmpty);
                setHomePhoneAttribute(req, homePhone, validHomePhone);
                setEmailAttribute(req, email, validEmail);

                session.setAttribute("add", true);
            }
        }
    }

    private void setSurnameAttribute(HttpServletRequest req, String surname, boolean validSurname, boolean surnameEmpty) {
        req.setAttribute("emptySurname", surnameEmpty);
        req.setAttribute("validSurname", validSurname);
        req.setAttribute("surname", surname);
    }

    private void setNameAttribute(HttpServletRequest req, String subscriberName, boolean validSubscriberName, boolean subscriberNameEmpty) {
        req.setAttribute("emptySubscriberName", subscriberNameEmpty);
        req.setAttribute("validSubscriberName", validSubscriberName);
        req.setAttribute("newSubscriberName", subscriberName);
    }

    private void setPatronymicAttribute(HttpServletRequest req, String patronymic, boolean validPatronymic, boolean patronymicEmpty) {
        req.setAttribute("patronymicEmpty", patronymicEmpty);
        req.setAttribute("validPatronymic", validPatronymic);
        req.setAttribute("patronymic", patronymic);
    }

    private void setMobilPhoneAttribute(HttpServletRequest req, String mobileNumber, boolean validMobileNumber, boolean mobileNumberEmpty) {
        req.setAttribute("mobileNumberEmpty", mobileNumberEmpty);
        req.setAttribute("validMobileNumber", validMobileNumber);
        req.setAttribute("mobileNumber", mobileNumber);
    }

    private void setHomePhoneAttribute(HttpServletRequest req, String homePhone, boolean validHomePhone) {
        req.setAttribute("validHomePhone", validHomePhone);
        req.setAttribute("homePhone", homePhone);
    }

    private void setEmailAttribute(HttpServletRequest req, String email, boolean validEmail) {
        req.setAttribute("validEmail", validEmail);
        req.setAttribute("email", email);
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