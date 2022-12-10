package ru.stqa.pft.mantis.appmanager;


import org.openqa.selenium.By;

public class LoginHelper extends HelperBase{

    public LoginHelper(ApplicationManager app) {
        super(app);
    }
    public void loginAsAdmin(String username, String password) {
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Войти']"));
    }
}
