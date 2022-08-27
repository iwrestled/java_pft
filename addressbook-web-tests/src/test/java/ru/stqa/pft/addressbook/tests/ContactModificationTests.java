package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() throws Exception {
        app.getContactHelper().selectContactInList();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("ChangedName", "ChangedMiddleName", "ChangedLastName", "changed@test.com"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnHomePage();
    }

}


