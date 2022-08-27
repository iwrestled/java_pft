package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"),contactData.getMiddleName());
        type(By.name("lastname"),contactData.getLastName());
        type(By.name("email"),contactData.getEmail());
    }

    public void selectContactInList() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact() {
        click((By.xpath("//input[@value='Delete']")));
    }

    public void initContactModification() {
        click((By.xpath("//img[@alt='Edit']")));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void returnHomePage() {
        click(By.linkText("home page"));
    }
// перенес в navigationHelper
//    public void gotoAddNewContact() {
//        click(By.linkText("add new"));
//    }
}
