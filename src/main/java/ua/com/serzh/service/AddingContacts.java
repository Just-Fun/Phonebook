package ua.com.serzh.service;

import ua.com.serzh.dao.ContactDao;
import ua.com.serzh.entities.Contact;
import ua.com.serzh.entities.User;
import ua.com.serzh.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Serzh on 11/2/16.
 */
public class AddingContacts {

    // TODO make GET & POST?
    public void addContact(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user) {
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
                Contact contact = new Contact(name, surname, patronymic, mobileNumber,
                        homePhone, address, email, user.getUserId());
                contactDao.insertContact(contact);

//                req.setAttribute("validMobileNumber", true); // TODO don't need?
                session.setAttribute("add", false);
                session.setAttribute("listChanged", true);

                req.setAttribute("info", true);
                String textInfo = surname + " " + name + " was added to the list of contacts";
                req.setAttribute("textInfo", textInfo);

                // TODO autowired
                new ContactManager().showContacts(contactDao, user, session);
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
}
