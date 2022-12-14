package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDataTests extends TestBase{

    @Test
    public void testContactData(){
        app.goTo().mainPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllMails(), equalTo(mergeMails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactDataTests::cleaned)
                .collect(Collectors.joining("\n"));
    }
    private String mergeMails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
    public static String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }

}
