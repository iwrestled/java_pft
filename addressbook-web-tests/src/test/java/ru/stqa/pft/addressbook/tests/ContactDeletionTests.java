package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().mainPage();
        if (app.contact().list().size()==0) {
            app.contact().create(new ContactData()
                    .withFirstName("TestFirstName").withMiddleName("TestMiddleName").withLastName("TestLastName").withEmail("test@test.com").withGroup("ChangedName"));
            app.contact().returnHomePage();
        }
    }


    @Test
    public void testContactDeletion() throws Exception {

        List<ContactData> before = app.contact().list();
        app.contact().selectInList(before.size() -1);
        app.contact().deleteSelected();
        app.goTo().acceptDialog();                      // вынести в contacthelper, проблема что часть в контакт, часть в goTo, разнести по разным хелперам?
        app.goTo().mainPage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size() - 1);
    }


}