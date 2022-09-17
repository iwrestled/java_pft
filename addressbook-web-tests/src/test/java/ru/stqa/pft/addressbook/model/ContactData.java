package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String email;
    private String group;

    public ContactData(String FirstName, String MiddleName, String LastName, String Email,String group) {
        firstName = FirstName;
        middleName = MiddleName;
        lastName = LastName;
        email = Email;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }
}
