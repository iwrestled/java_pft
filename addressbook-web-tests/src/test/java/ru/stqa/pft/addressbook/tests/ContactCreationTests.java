package ru.stqa.pft.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;



public class ContactCreationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size()==0){
      app.goTo().groupPage();
      app.group().newGroup();
      app.group().create(new GroupData().withName("test 0"));
    }

//    app.goTo().mainPage();
//    app.contact().addNew();
//    if (! app.contact().isGroupExists()){
//     app.goTo().groupPage();
//      app.group().newGroup();
//      app.group().create(new GroupData().withName("ChangedName").withHeader("AutoCreatedInContacts"));
//      app.goTo().mainPage();
//    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))){
      String json = "";
      String line = reader.readLine();
      while (line != null){
        json += line;
        line=reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();  // Заворачиваем объект в массив
    }
  }

  @Test (enabled = false)
  public void testContactCreation() throws Exception {
    Groups groups = app.db().groups();
    app.goTo().mainPage();
    Contacts before = app.db().contacts();
    File photo = new File("src/test/resources/stru.jpg");
    ContactData contact = new ContactData()
            .withId(app.contact().getContactsMaxID(before)+1).withFirstName("zTestFirstName").withLastName("zTestLastName").withEmail("test@test.com").withGroup("test 0")
            .withPhoto(photo);
    app.contact().create(contact);
    app.contact().returnHomePage();
    assertThat(app.contact().getContactCount(),equalTo(before.size() +1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (dataProvider = "validContactsFromJson")
  public void testContactCreationGenerator(ContactData contact) throws Exception {
    app.goTo().mainPage();
    Contacts before = app.db().contacts();
    app.contact().createGenerator(contact);
    app.contact().returnHomePage();
    assertThat(app.contact().getContactCount(),equalTo(before.size() +1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/stru.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
