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
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().gotoAddNewContact();
    app.getContactHelper().fillContactForm(new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "test@test.com","ChangedName"),true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before +1);

  }

}
