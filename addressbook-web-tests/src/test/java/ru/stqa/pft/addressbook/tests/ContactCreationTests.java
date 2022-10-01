package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;



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
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData()
            .withId(app.contact().getContactsMaxID(before)+1).withFirstName("TestFirstName").withLastName("TestLastName").withEmail("test@test.com").withGroup("ChangedName");
//    ContactData contact = new ContactData(app.contact().getContactsMaxID(before)+1,"", "TestMiddleName", "TestLastName", "test@test.com","ChangedName");
    app.contact().create(contact);
    app.contact().returnHomePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(),before.size() +1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before,after);

  }
}
