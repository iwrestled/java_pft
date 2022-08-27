package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }
    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void gotoAddNewContact() {
        click(By.linkText("add new"));
    }

    public void gotoMainPage() {
        click(By.linkText("home"));
    }

    public void acceptDialog() {
        wd.switchTo().alert().accept();
    }
}
