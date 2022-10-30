package pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {
  protected WebDriver driver;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;
  JavascriptExecutor js;

  public void init() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    driver.get("http://localhost/addressbook/");
    driver.manage().window().setSize(new Dimension(1198, 804));
    groupHelper = new GroupHelper(driver);
    contactHelper = new ContactHelper(driver);
    login("admin", "secret");
  }

  public void login(String username, String password) {
    driver.findElement(By.name("user")).sendKeys(username);
    driver.findElement(By.name("pass")).sendKeys(password);
    driver.findElement(By.cssSelector("input:nth-child(7)")).click();
  }

  public void stop() {
    driver.quit();
  }

  public void goToGroupPage() {
    driver.findElement(By.linkText("groups")).click();
  }

  public void selectElement() {
    driver.findElement(By.name("selected[]")).click();
  }

  public void submitContactCreation() {
    groupHelper.submitGroupCreation();
  }

  public void goToContactAddPage() {
    driver.findElement(By.linkText("add new")).click();
  }

  public void goToHomePage() {
    driver.findElement(By.linkText("home")).click();
  }

  public void returnToHomePage() {
    driver.findElement(By.linkText("home page")).click();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
