package ua.com.serzh.entities;

public class Contact {
    private Integer contactId;
    private String name;
    private String mobileNumber;
    private Integer userId;

    public Contact() {
    }

    public Contact(String name, String mobileNumber, Integer userId) {
        this.name = name;
        this.mobileNumber = mobileNumber;
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
}
