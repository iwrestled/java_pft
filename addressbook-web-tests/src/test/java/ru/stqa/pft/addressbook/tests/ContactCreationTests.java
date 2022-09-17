package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {

    app.getContactHelper().gotoAddNewContact();
    app.getContactHelper().fillContactForm(new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "test@test.com","ChangedName"),true);
    app.getContactHelper().submitContactCreation();
  }

}
