package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase{

//    @BeforeMethod
//    public void startMailServer(){
//        app.mail().start();
//    }

    @Test
    public void testPasswordChange() throws MessagingException, IOException, InterruptedException {


//    String user = "user1670774556040";
    String user = app.db().getUserInfo().getUsername();
    String password = "password";
    String email = app.db().getUserInfo().getEmail();
    app.james().deleteUser(user);
    app.james().createUser(user,password);  //нужно для создания нового пользователя в почте
    app.login().loginAsAdmin(app.getProperty("web.adminLogin"),app.getProperty("web.adminPassword"));
    app.goTo().managePage();
    app.goTo().manageUsers();
    app.contact().chooseContactById(app.db().getUserInfo().getId());
    app.contact().resetPassword();
    Thread.sleep(3500);
    //List<MailMessage> mailMessages = app.mail().waitForMail(2, 15000); //wiser
    List<MailMessage> mailMessages = app.james().waitForMail(user,password,60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    Thread.sleep(3500);
    HttpSession session = app.newSession();
    assertTrue(session.login(user,password));
//    assertTrue(session.isLoggedInAs("user"));  // проверка входа
    }

   private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream()
                .filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }


//    @AfterMethod(alwaysRun = true)
//    public void stopMailServer(){
//        app.mail().stop();
//    }

}
