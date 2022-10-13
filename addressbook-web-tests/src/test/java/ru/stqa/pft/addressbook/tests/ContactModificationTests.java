package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
        public void ensurePreconditions() {
        if (app.db().contacts().size()==0) {
            app.goTo().mainPage();
            app.contact().create(new ContactData()
                    .withFirstName("TestFirstName").withMiddleName("TestMiddleName").withLastName("TestLastName").withEmail("test@test.com").withGroup("ChangedName"));
            app.contact().returnHomePage();
        }
    }

    @Test
    public void testContactModification() throws Exception {
        app.goTo().mainPage();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        File photo = new File("src/test/resources/stru.jpg");
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("ChangedFirstName").withLastName("ChangedLastName").withEmail("Changed@test.com").withGroup("ChangedName").withPhoto(photo);
        app.contact().modify(contact);
        assertThat(app.contact().getContactCount(),equalTo(before.size()));
        Contacts after = app.db().contacts();
//        assertEquals(after.size(),before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

    @Test
    public void testContactModificationGenerator() throws Exception {
        app.goTo().mainPage();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        File photo = new File("src/test/resources/stru.jpg");
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("ChangedFirstName").withMiddleName("ChangedMiddleName").withLastName("ChangedLastName").withEmail("Changed@test.com").withGroup("ChangedName").withPhoto(photo);
        app.contact().modify(contact);
        assertThat(app.contact().getContactCount(),equalTo(before.size()));
        Contacts after = app.db().contacts();
//        assertEquals(after.size(),before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}


