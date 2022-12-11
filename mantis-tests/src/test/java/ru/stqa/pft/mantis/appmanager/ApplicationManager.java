package ru.stqa.pft.mantis.appmanager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;

    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private ContactHelper contactHelper;
    private LoginHelper loginHelper;
    private NavigationHelper navigationHelper;
    private JamesHelper jamesHelper;

    private DbHelper dbHelper;


    public ApplicationManager(String browser)  {
        this.browser = browser;
        properties = new Properties();
    }
    public WebDriver getDriver() {
        if (wd == null){
            if (browser.equals(BrowserType.CHROME)){
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.FIREFOX)){
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }
            wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }
    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }
    public void stop() {
        if(wd != null){
            wd.quit();
        }
    }
    public HttpSession newSession(){
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null){
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public ContactHelper contact() {
        if (contactHelper == null){
            contactHelper = new ContactHelper(this);
        }
        return contactHelper;
    }
   public LoginHelper login() {
        if (loginHelper == null){
            loginHelper = new LoginHelper(this);
        }
        return loginHelper;
    }

    public NavigationHelper goTo() {
        if (navigationHelper == null){
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }

    public FtpHelper ftp(){
        if (ftp == null){
            ftp = new FtpHelper(this);
        }
        return ftp;
    }
    public MailHelper mail(){
        if (mailHelper == null){
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james(){
        if (jamesHelper == null){
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public DbHelper db (){
        if (dbHelper == null){
            dbHelper = new DbHelper(this);
        }
        return dbHelper;
    }


}
