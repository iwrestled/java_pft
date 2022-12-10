package ru.stqa.pft.mantis.appmanager;


import org.openqa.selenium.By;

public class ContactHelper extends HelperBase{

    public ContactHelper(ApplicationManager app) {
        super(app);
    }


    public void chooseContact(){
        wd.findElement(By.xpath("//a[@href='manage_user_edit_page.php?user_id=7']")).click();  // хардкодом берем с определнным айди
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

}
