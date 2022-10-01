package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;



public class ContactCreationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().mainPage();
    app.contact().addNew();
    if (! app.contact().isGroupExists()){
      app.goTo().groupPage();
      app.group().newGroup();
      app.group().create(new GroupData().withName("ChangedName").withHeader("AutoCreatedInContacts"));
      app.goTo().mainPage();
    }
  }

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().mainPage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withId(app.contact().getContactsMaxID(before)+1).withFirstName("zTestFirstName").withLastName("zTestLastName").withEmail("test@test.com").withGroup("ChangedName");
    app.contact().create(contact);
    app.contact().returnHomePage();
    Contacts after = app.contact().all();
    assertThat(after.size(),equalTo(before.size() +1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
