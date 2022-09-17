package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
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

    public void createContact(ContactData contact) {
        gotoAddNewContact();
        fillContactForm((contact),true);
        submitContactCreation();
    }
    public void gotoAddNewContact() {
        click(By.linkText("add new"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
}
