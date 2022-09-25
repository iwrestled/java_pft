package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String email;
    private String group;

    public ContactData(String FirstName, String MiddleName, String LastName, String Email,String group) {
        this.id = Integer.MAX_VALUE;
        firstName = FirstName;
        middleName = MiddleName;
        lastName = LastName;
        email = Email;
        this.group = group;
    }
    public ContactData(int id,String FirstName, String MiddleName, String LastName, String Email,String group) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    public void setId(int id) {
        this.id = id;
    }
}
