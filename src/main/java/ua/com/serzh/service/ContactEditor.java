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
public class ContactEditor {

    Contact contact;

    private boolean validSurname = false;
    private boolean validName = false;
    private boolean validPatronymic = false;
    private boolean validMobileNumber = false;
    private boolean validHomePhone = true; // if empty - don't validate(field can be NULL)
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

    public boolean addContact(HttpServletRequest request, ContactDao contactDao, HttpSession session, User user) {

        getFields(request);
        checkFieldsIsEmpty();
        validateFields();

        return insertContact(request, contactDao, session, user);
    }

    public boolean editContact(HttpServletRequest request, HttpSession session, ContactDao contactDao) {

        String contactId = request.getParameter("select");

        if (contactId != null) {
            contact = contactDao.searchContactById(Integer.parseInt(contactId));
            session.setAttribute("contact", contact);
            return false;
        } else {
            contact = (Contact) session.getAttribute("contact");
        }

        getFields(request);
        checkFieldsIsEmpty();
        validateFields();

        return updateContact(request, session, contactDao, contact);
    }

    private boolean updateContact(HttpServletRequest request, HttpSession session, ContactDao contactDao, Contact contact) {
        if (validSurname && validName && validPatronymic && validMobileNumber & validHomePhone & validEmail) {
            setContactFields(contact);

            contactDao.updateContact(contact);

            String textInfo = "Contact was updated";
            setAttributesTrue(request, textInfo);

            session.setAttribute("edit", false);
            return true;
        } else {
            setAttributesFalse(request);
            session.setAttribute("edit", true);
            return false;
        }
    }

    private void setContactFields(Contact contact) {
        contact.setSurname(surname);
        contact.setName(name);
        contact.setPatronymic(patronymic);
        contact.setMobileNumber(mobileNumber);
        contact.setHomePhone(homePhone);
        contact.setAddress(address);
        contact.setEmail(email);
    }

    private void getFields(HttpServletRequest request) {
        surname = request.getParameter("surname");
        name = request.getParameter("name");
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

    private boolean insertContact(HttpServletRequest request, ContactDao contactDao, HttpSession session, User user) {
        if (validSurname && validName && validPatronymic && validMobileNumber & validHomePhone & validEmail) {

            insertNewContact(contactDao, user);

            session.setAttribute("add", false);

            String textInfo = String.format("'%s %s' was added to the list of contacts", surname, name);
            setAttributesTrue(request, textInfo);

            return true;
        } else {
            setAttributesFalse(request);
            session.setAttribute("add", true);
            return false;
        }
    }

    private void insertNewContact(ContactDao contactDao, User user) {
        Contact contact = new Contact(
                surname, name, patronymic, mobileNumber, homePhone, address, email, user.getUserId());

        contactDao.insertContact(contact);
    }

    private void setAttributesTrue(HttpServletRequest request, String message) {
        request.setAttribute("info", true);
        request.setAttribute("textInfo", message);
    }

    private void setAttributesFalse(HttpServletRequest request) {
        setSurnameAttribute(request);
        setNameAttribute(request);
        setPatronymicAttribute(request);
        setMobilPhoneAttribute(request);
        setHomePhoneAttribute(request);
        setEmailAttribute(request);
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
        request.setAttribute("name", name);
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