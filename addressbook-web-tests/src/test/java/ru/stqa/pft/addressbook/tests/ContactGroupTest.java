package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGroupTest extends TestBase{

    int contactId;
    int groupId;

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size()==0){
            app.goTo().groupPage();
            app.group().newGroup();
            app.group().create(new GroupData().withName("test 0"));
        }
        if (app.db().contacts().size()==0) {
            Groups groups = app.db().groups();
            app.goTo().mainPage();
            File photo = new File("src/test/resources/stru.jpg");
            app.contact().create(new ContactData()
                    .withFirstName("TestFirstName").withMiddleName("TestMiddleName").withLastName("TestLastName")
                    .inGroup(groups.iterator().next())
                    .withPhoto(photo)
                    .withEmail("test@test.com"));
            app.contact().returnHomePage();
        }
        if (app.db().contacts().size() > 0) {
            List<ContactData> contacts = new ArrayList<ContactData>(app.db().contacts());
            for (ContactData contact : contacts) {
                if (contact.getGroups().size() == 0) {
                    contactId = contact.getId();
                    break;
                }
            }
            if (contactId==0) {
                app.goTo().mainPage();
                File photo = new File("src/test/resources/stru.jpg");
                app.contact().create(new ContactData()
                        .withFirstName("TestFirstName").withMiddleName("TestMiddleName").withLastName("TestLastName").withPhoto(photo).withEmail("test@test.com"));
                app.contact().returnHomePage();
                contactId = app.db().contacts().stream().mapToInt((g) -> g.getId()).max().getAsInt();
            }
        }
    }
    @Test
    public void testContactGroupAdd() {
        Groups before = app.db().getContactById(contactId).getGroups();
        app.goTo().mainPage();
        app.contact().selectInListById(contactId);
        String groupIdStr = app.contact().getSelectedGroupId();
        app.contact().addGroup();
        app.contact().proceedToContactsWithGroupTest();
        groupId= Integer.parseInt(groupIdStr);
        GroupData groupData = app.db().getGroupById(groupId);
        Groups after = app.db().getContactById(contactId).getGroups();
        assertThat(after, equalTo(before.withAdded(groupData)));
    }
    @Test
    public void testContactGroupDeletion() {
        Groups before = app.db().getContactById(contactId).getGroups();
        app.contact().selectGroupInListById(groupId);
        app.contact().selectInListById(contactId);
        app.contact().removeGroup();
        app.contact().proceedToContactsWithGroupTest();
        GroupData groupData = app.db().getGroupById(groupId);
        Groups after = app.db().getContactById(contactId).getGroups();
        assertThat(after, equalTo(before.without(groupData)));

    }
}



