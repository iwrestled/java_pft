package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

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
    File photo = new File("src/test/resources/stru.jpg");
    ContactData contact = new ContactData()
            .withId(app.contact().getContactsMaxID(before)+1).withFirstName("zTestFirstName").withLastName("zTestLastName").withEmail("test@test.com").withGroup("ChangedName")
            .withPhoto(photo);
    app.contact().create(contact);
    app.contact().returnHomePage();
    assertThat(app.contact().getContactCount(),equalTo(before.size() +1));
    Contacts after = app.contact().all();
//    assertThat(after.size(),equalTo(before.size() +1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
  @Test
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/stru.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
