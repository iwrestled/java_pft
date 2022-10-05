package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

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

    public void selectInListById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelected() {
        click((By.xpath("//input[@value='Delete']")));
    }

    public void initModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
        //click((By.xpath("//img[@alt='Edit']")));
    }
    public void initModificationById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
//        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
    }

    public void submitModification() {
        click(By.name("update"));
    }

    public void returnHomePage() {
        click(By.linkText("home page"));
    }

    public void create(ContactData contact) {
        addNew();
        fillForm((contact),true);
        submitContactCreation();
        contactCache=null;
    }
    public void modify(ContactData contact) {
        initModificationById(contact.getId());
        fillForm(contact,false);
        submitModification();
        contactCache=null;
        returnHomePage();
    }
    public void delete(int index) {
        selectInList(index);
        deleteSelected();
        contactCache=null;
    }
    public void delete(ContactData contact) {
        selectInListById(contact.getId());
        deleteSelected();
        contactCache=null;
    }

    public void addNew() {
        click(By.linkText("add new"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

//   public List<ContactData> list() {
  //      List<ContactData> contacts = new ArrayList<ContactData>();
 //       List<WebElement> elements = wd.findElements(By.name("entry"));
 //       for (WebElement cells : elements) {
//
 //           List<WebElement> columns = cells.findElements(By.tagName("td"));
  //          String firstName = columns.get(2).getText();
 //           String lastName = columns.get(1).getText();
 //           int id = Integer.parseInt(cells.findElement(By.tagName("input")).getAttribute("value"));
//            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
//            }
//            return contacts;
//        }

    private Contacts contactCache = null;

    public Contacts all() {
            if (contactCache != null){
                return new Contacts (contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement cells : elements) {

            List<WebElement> columns = cells.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.findElement(By.tagName("input")).getAttribute("value"));
            String firstName = columns.get(2).getText();
            String lastName = columns.get(1).getText();
//            String[] phones = columns.get(5).getText().split("\n");
            String address = columns.get(3).getText();
            String allMails = columns.get(4).getText();
            String allPhones = columns.get(5).getText();
            contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAllMails(allMails)
                    .withAllPhones(allPhones)
                    .withAddress(address));
        }
        return new Contacts(contactCache);
    }

    public int getContactsMaxID(Set<ContactData> contacts) {
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


    public ContactData infoFromEditForm(ContactData contact) {
        initModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.xpath("//*[@id=\"content\"]/form[1]/textarea[1]")).getText();

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withEmail(email).withEmail2(email2).withEmail3(email3)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withAddress(address);

    }
}
