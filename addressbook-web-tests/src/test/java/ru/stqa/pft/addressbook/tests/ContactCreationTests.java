package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class ContactCreationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().mainPage();
    app.contact().gotoAddNewContact();
    if (! app.contact().isGroupExists()){
      app.goTo().groupPage();
      app.group().initGroupCreation();
      app.group().create(new GroupData().withName("ChangedName").withHeader("AutoCreatedInContacts"));
      app.goTo().mainPage();
    }
  }

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().mainPage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withId(app.contact().getContactsMaxID(before)+1).withFirstName("zTestFirstName").withLastName("zTestLastName").withEmail("test@test.com").withGroup("ChangedName");
    app.contact().create(contact);
    app.contact().returnHomePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(),before.size() +1);


    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
//    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
//    before.sort(byId);
//    after.sort(byId);

    Assert.assertEquals(before,after);

  }
}
