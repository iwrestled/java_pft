package ru.stqa.pft.addressbook;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String email;

    public ContactData(String FirstName, String MiddleName, String LastName, String Email) {
        firstName = FirstName;
        middleName = MiddleName;
        lastName = LastName;
        email = Email;
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
}
