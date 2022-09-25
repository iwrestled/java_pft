package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoMainPage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoAddNewContact();
    if (! app.getContactHelper().isGroupExists()){
      app.getGroupHelper().gotoGroupPage();
      app.getGroupHelper().createGroup(new GroupData("ChangedName", null, null));
      app.getContactHelper().gotoAddNewContact();
    }
    app.getContactHelper().fillContactForm(new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "test@test.com","ChangedName"),true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    System.out.println(after);
    Assert.assertEquals(after.size(),before.size() +1);

  }
}
