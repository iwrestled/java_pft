package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String group;

    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String allPhones;
    private String email2;
    private String email3;
    private String allMails;
    private String address;



    public int getId() {
        return id;
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


    public String getGroup() {
        return group;
    }

    public String getHomePhone() {
        return homePhone;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }
    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllMails() {
        return allMails;
    }
    public String getAddress() {
        return address;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }


    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }


    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }


    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }


    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }
    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }
    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllMails(String allMails) {
        this.allMails = allMails;
        return this;
    }
    public ContactData withAddress(String address) {
        this.address = address;
        return this;
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



}
