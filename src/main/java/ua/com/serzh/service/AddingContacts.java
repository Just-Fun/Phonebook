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

    private boolean validSurname = false;
    private boolean validName = false;
    private boolean validPatronymic = false;
    private boolean validMobileNumber = false;
    private boolean validHomePhone = true; // if empty - don't validate
    private boolean validEmail = true; // the same

    private boolean surnameEmpty;
    private boolean nameEmpty;
    private boolean patronymicEmpty;
    private boolean mobileNumberEmpty;

    private String surname;
    private String name;
    private String mobileNumber;
    private String patronymic;
    private String homePhone;
    private String address;
    private String email;

    public boolean run(HttpServletRequest request, ContactDao contactDao, HttpSession session, User user) {
        if ("Cancel".equals(request.getParameter("cancel"))) {
            session.setAttribute("add", false);
            return false;
        }

        surname = request.getParameter("surname");
        if (surname == null) {
            return false;
        }

        getFields(request);

        checkFieldsIsEmpty();

        validateFields();

       /* boolean success = false;
        if ("Ok".equals(request.getParameter("ok"))) {
            success = addContact(request, contactDao, session, user);
        }
        return success;*/

        return addContact(request, contactDao, session, user);
    }

    private void getFields(HttpServletRequest request) {
        name = request.getParameter("newSubscriberName");
        mobileNumber = request.getParameter("mobileNumber");
        patronymic = request.getParameter("patronymic");
        homePhone = request.getParameter("homePhone");
        address = request.getParameter("address");
        email = request.getParameter("email");
    }

    private void checkFieldsIsEmpty() {
        surnameEmpty = surname.isEmpty();
        nameEmpty = name.isEmpty();
        patronymicEmpty = name.isEmpty();
        mobileNumberEmpty = mobileNumber.isEmpty();
    }

    private void validateFields() {
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
    }

    private boolean addContact(HttpServletRequest request, ContactDao contactDao, HttpSession session, User user) {
        if (validSurname && validName && validPatronymic && validMobileNumber & validHomePhone & validEmail) {

            insertNewContact(contactDao, user);

            setAttributesTrue(request, session);

            return true;
        } else {
            setAttributesFalse(request, session);
            return false;
        }
    }

    private void insertNewContact(ContactDao contactDao, User user) {
        Contact contact = new Contact(
                surname, name, patronymic, mobileNumber, homePhone, address, email, user.getUserId());

        contactDao.insertContact(contact);
    }

    private void setAttributesTrue(HttpServletRequest request, HttpSession session) {
        session.setAttribute("add", false);
        session.setAttribute("listChanged", true);

        request.setAttribute("info", true);
        String textInfo = surname + " " + name + " was added to the list of contacts";
        request.setAttribute("textInfo", textInfo);
    }

    private void setAttributesFalse(HttpServletRequest request, HttpSession session) {
        setSurnameAttribute(request);
        setNameAttribute(request);
        setPatronymicAttribute(request);
        setMobilPhoneAttribute(request);
        setHomePhoneAttribute(request);
        setEmailAttribute(request);

        session.setAttribute("add", true);
    }

    // TODO removed duplication
    private void setSurnameAttribute(HttpServletRequest request) {
        request.setAttribute("emptySurname", surnameEmpty);
        request.setAttribute("validSurname", validSurname);
        request.setAttribute("surname", surname);
    }

    private void setNameAttribute(HttpServletRequest request) {
        request.setAttribute("emptySubscriberName", nameEmpty);
        request.setAttribute("validSubscriberName", validName);
        request.setAttribute("newSubscriberName", name);
    }

    private void setPatronymicAttribute(HttpServletRequest request) {
        request.setAttribute("patronymicEmpty", patronymicEmpty);
        request.setAttribute("validPatronymic", validPatronymic);
        request.setAttribute("patronymic", patronymic);
    }

    private void setMobilPhoneAttribute(HttpServletRequest request) {
        request.setAttribute("mobileNumberEmpty", mobileNumberEmpty);
        request.setAttribute("validMobileNumber", validMobileNumber);
        request.setAttribute("mobileNumber", mobileNumber);
    }

    private void setHomePhoneAttribute(HttpServletRequest request) {
        request.setAttribute("validHomePhone", validHomePhone);
        request.setAttribute("homePhone", homePhone);
    }

    private void setEmailAttribute(HttpServletRequest request) {
        request.setAttribute("validEmail", validEmail);
        request.setAttribute("email", email);
    }
}