package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletion() throws Exception {
        if (! app.getContactHelper().isThereAContact()){
           app.getContactHelper().createContact(new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "test@test.com","ChangedName"));
            app.getContactHelper().returnHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContactInList(before.size() -1);
        app.getContactHelper().deleteSelectedContact();
        app.goTo().acceptDialog();
        app.goTo().gotoMainPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() - 1);
    }
}