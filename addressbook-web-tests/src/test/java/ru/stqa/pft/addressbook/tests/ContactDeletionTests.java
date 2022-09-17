package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;


public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletion() throws Exception {
        if (! app.getContactHelper().isThereAContact()){
           app.getContactHelper().createContact(new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "test@test.com","ChangedName"));
            app.getContactHelper().returnHomePage();
        }
        app.getContactHelper().selectContactInList();
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().acceptDialog();
        app.getNavigationHelper().gotoMainPage();
    }
}