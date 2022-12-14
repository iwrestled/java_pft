package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;


public class RegistrationHelper extends HelperBase{

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
//        click(By.cssSelector("input[value='Signup']"));
        click(By.cssSelector("input[value='Зарегистрироваться']"));  //ищем элемент input у которого аттрибут value = Signup

    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
//        click(By.cssSelector("input[value='Update User']"));
        click(By.cssSelector("input[value='Изменить учетную запись']"));
    }

/*    public void finishChangePass(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.id("password"), password);
        type(By.id("password-confirm"), password);
        click(By.cssSelector("input[value='Изменить учетную запись']"));
    }
 */
}
