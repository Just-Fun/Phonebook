package ua.com.serzh.service;

import ua.com.serzh.dao.ContactDao;
import ua.com.serzh.entities.Contact;
import ua.com.serzh.entities.User;
import ua.com.serzh.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Serzh on 11/3/16.
 */
public class EditingContact {

    private boolean validName;
/*
    public void editContact(HttpServletRequest request, ContactDao contactDao, HttpSession session, User user) {

        String contactId = request.getParameter("select");

        Contact contact = (Contact) session.getAttribute("contact");
        if (contactId != null) {
            contact = contactDao.searchContactById(Integer.parseInt(contactId));
            session.setAttribute("contact", contact);
        }



        String subscriberName = request.getParameter("editName");

          if (subscriberName == null) {
            return;
        }


        String mobileNumber = request.getParameter("mobileNumber");

        if (!subscriberName.isEmpty()) {
            validName = Validation.validate(subscriberName, Validation.getFourLettersPattern());
        }
        if (!validName) {
            request.setAttribute("emptySubscriberName", subscriberName.isEmpty());
            request.setAttribute("validSubscriberName", validName);
            request.setAttribute("name", subscriberName);
            return;
        }


        if ("Ok".equals(request.getParameter("ok"))) {
            if (contact != null) {
                contact.setName(subscriberName);
                contact.setMobileNumber(mobileNumber);
                contactDao.updateContact(contact);

                session.setAttribute("edit", false);
                session.setAttribute("listChanged", true);
                // TODO do the same as addingContact
                new ContactManager().showContacts(contactDao, user, session);
            }
            // TODO moved to begin
        } else if ("Cancel".equals(request.getParameter("cancel"))) {
            session.setAttribute("edit", false);
        }
    }*/
}
