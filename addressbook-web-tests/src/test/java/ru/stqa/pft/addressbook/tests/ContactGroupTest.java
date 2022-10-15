package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGroupTest extends TestBase{

    @BeforeMethod (enabled = false)
    public void ensurePreconditions() {
        if (app.db().groups().size()==0){
            app.goTo().groupPage();
            app.group().newGroup();
            app.group().create(new GroupData().withName("test 0"));
        }
        if (app.db().contacts().size()==0) {
            Groups groups = app.db().groups();
            app.goTo().mainPage();
            app.contact().create(new ContactData()
                    .withFirstName("TestFirstName").withMiddleName("TestMiddleName").withLastName("TestLastName")
                    .inGroup(groups.iterator().next())
                    .withEmail("test@test.com"));
            app.contact().returnHomePage();
        }
    }
    @Test
    public void testContactGroupAdd() {
        app.goTo().mainPage();
        Contacts before = app.db().contacts();
        ContactData contactGroup = before.iterator().next();
        app.contact().groupAdding(contactGroup);

        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(contactGroup).withAdded(contactGroup)));
        verifyGroupListInUI();

//        app.contact().removeAddedGroup(contactGroup);

//        assertThat(app.group().count(),equalTo(before.size() +1));
//        Groups after = app.db().groups();
//        assertThat(after, equalTo(
//                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }













}
