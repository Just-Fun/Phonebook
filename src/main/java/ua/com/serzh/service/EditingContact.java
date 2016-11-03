package ua.com.serzh.service;

import ua.com.serzh.dao.ContactDao;
import ua.com.serzh.entities.Contact;
import ua.com.serzh.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Serzh on 11/3/16.
 */
public class EditingContact {

    public void editContact(HttpServletRequest req, ContactDao contactDao, HttpSession session, User user) {
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
                // TODO do the same as addingContact
                new ContactManager().showContacts(contactDao, user, session);
            }
            // TODO moved to begin
        } else if ("Cancel".equals(req.getParameter("cancel"))) {
            session.setAttribute("edit", false);
        }
    }
}
