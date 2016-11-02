package ua.com.serzh.entities;

/**
 * Created by Serzh on 10/25/16.
 */
public class Contact {
    private Integer contactId;
    private String surname;
    private String name;
    private String patronymic;
    private String mobileNumber;

    private String homePhone;
    private String address;
    private String email;

    private Integer userId;

    public Contact() {
    }

    public Contact(String surname, String name, String patronymic, String mobileNumber,
                   String homePhone, String address, String email, Integer userId) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.mobileNumber = mobileNumber;
        this.homePhone = homePhone;
        this.address = address;
        this.email = email;
        this.userId = userId;
    }

    public Integer getContactId() {
        return this.contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
