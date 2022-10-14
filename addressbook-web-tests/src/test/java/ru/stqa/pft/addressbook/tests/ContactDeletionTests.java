package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size()==0) {
            app.goTo().mainPage();
            app.contact().create(new ContactData()
                    .withFirstName("TestFirstName").withMiddleName("TestMiddleName").withLastName("TestLastName").withEmail("test@test.com"));
            app.contact().returnHomePage();
        }
    }

    @Test
    public void testContactDeletion() throws Exception {

        Contacts before = app.db().contacts();
        app.goTo().mainPage();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().acceptDialog();
        app.goTo().mainPage();
        assertThat(app.contact().getContactCount(),equalTo(before.size() -1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}