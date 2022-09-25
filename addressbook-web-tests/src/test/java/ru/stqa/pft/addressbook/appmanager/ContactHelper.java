package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

    public void selectContactInList(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
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

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public ArrayList<ContactData> getContactList() {
        ArrayList<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> cells = wd.findElements(By.tagName("td"));
        for (WebElement element : cells) {
                String firstName = element[3].getText();
              //  String lastName = cells[2].getText();
                //    int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
                ContactData contact = new ContactData(firstName, null, null, null, null);
                contacts.add(contact);
            }
        return contacts;
    }

    public boolean isGroupExists() {
        return isElementPresent(By.xpath("//*[.='ChangedName']"));
    }
}
