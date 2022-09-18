package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() throws Exception {
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "test@test.com","ChangedName"));
            app.getContactHelper().returnHomePage();
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContactInList(before -1);
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("ChangedName", "ChangedMiddleName", "ChangedLastName", "changed@test.com",null),false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after,before);
    }

}


