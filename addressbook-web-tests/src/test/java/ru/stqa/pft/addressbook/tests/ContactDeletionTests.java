package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;


public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().mainPage();
        if (app.contact().all().size()==0) {
            app.contact().create(new ContactData()
                    .withFirstName("TestFirstName").withMiddleName("TestMiddleName").withLastName("TestLastName").withEmail("test@test.com").withGroup("ChangedName"));
            app.contact().returnHomePage();
        }
    }


    @Test
    public void testContactDeletion() throws Exception {

        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().acceptDialog();
        app.goTo().mainPage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size() - 1);

        before.remove (deletedContact);
        Assert.assertEquals(before,after);



    }


}