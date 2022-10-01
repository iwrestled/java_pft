package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillForm(ContactData contactData, boolean creation) {
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

    public void selectInList(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelected() {
        click((By.xpath("//input[@value='Delete']")));
    }

    public void initModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
        //click((By.xpath("//img[@alt='Edit']")));
    }

    public void submitModification() {
        click(By.name("update"));
    }

    public void returnHomePage() {
        click(By.linkText("home page"));
    }

    public void create(ContactData contact) {
        gotoAddNewContact();
        fillForm((contact),true);
        submitContactCreation();
    }
    public void modify(int index, ContactData contact) {
        initModification(index);
        fillForm(contact,false);
        submitModification();
        returnHomePage();
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

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement cells : elements) {

            List<WebElement> columns = cells.findElements(By.tagName("td"));
            String firstName = columns.get(2).getText();
            String lastName = columns.get(1).getText();
            int id = Integer.parseInt(cells.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
            }
            return contacts;
        }

    public int getContactsMaxID(List<ContactData> contacts) {
        int max = 0;
        for (ContactData contact : contacts) {
            if (contact.getId() > max)
                max = contact.getId();
        }
        return max;
    }
    public boolean isGroupExists() {
        return isElementPresent(By.xpath("//*[.='ChangedName']"));
    }
}
