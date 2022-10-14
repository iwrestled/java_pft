package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name="addressbook")

public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String firstName;
    @Expose
    @Column(name = "middlename")
    private String middleName;
    @Expose
    @Column(name = "lastname")
    private String lastName;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;
//    @Expose
//    @Transient
//    private String group;
    @Expose
    @Column(name = "photo")
    @Type(type="text")
    private String photo;
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Transient
    private String allPhones;
    @Transient
    private String email2;
    @Transient
    private String email3;
    @Transient
    private String allMails;
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"),inverseJoinColumns = @JoinColumn(name="group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public File getPhoto() {
        return new File (photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }





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


//    public String getGroup() {
//        return group;
//    }

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

    public Groups getGroups() {
        return new Groups (groups);
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


//    public ContactData withGroup(String group) {
//        this.group = group;
//        return this;
//    }


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
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, email);
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
