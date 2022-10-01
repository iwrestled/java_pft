package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

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
    public void testContactModification() throws Exception {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("ChangedFirstName").withLastName("ChangedLastName").withEmail("Changed@test.com").withGroup("ChangedName");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(),before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}


