package pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import pft.model.ContactData;

public class ApplicationManager {
  private final GroupHelper groupHelper = new GroupHelper();
  JavascriptExecutor js;

  public void init() {
    groupHelper.driver = new ChromeDriver();
    js = (JavascriptExecutor) groupHelper.driver;
    groupHelper.driver.get("http://localhost/addressbook/");
    groupHelper.driver.manage().window().setSize(new Dimension(1198, 804));
    login("admin", "secret");
  }

  public void login(String username, String password) {
    groupHelper.driver.findElement(By.name("user")).sendKeys(username);
    groupHelper.driver.findElement(By.name("pass")).sendKeys(password);
    groupHelper.driver.findElement(By.cssSelector("input:nth-child(7)")).click();
  }

  public void stop() {
    groupHelper.driver.quit();
  }

  public void goToGroupPage() {
    groupHelper.driver.findElement(By.linkText("groups")).click();
  }

  public void deleteContact() {
    groupHelper.driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
  }

  public void selectElement() {
    groupHelper.driver.findElement(By.name("selected[]")).click();
  }

  public void submitContactCreation() {
    groupHelper.submitGroupCreation();
  }

  public void fillContactForm(ContactData contactData) {
    groupHelper.driver.findElement(By.name("firstname")).sendKeys(contactData.first_name());
    groupHelper.driver.findElement(By.name("lastname")).sendKeys(contactData.last_name());
    groupHelper.driver.findElement(By.name("company")).sendKeys(contactData.company());
    groupHelper.driver.findElement(By.name("address")).sendKeys(contactData.address());
    groupHelper.driver.findElement(By.name("mobile")).sendKeys(contactData.mobile_phone());
    groupHelper.driver.findElement(By.name("email")).sendKeys(contactData.email());
  }

  public void goToContactAddPage() {
    groupHelper.driver.findElement(By.linkText("add new")).click();
  }

  public void goToHomePage() {
    groupHelper.driver.findElement(By.linkText("home")).click();
  }

  public void returnToHomePage() {
    groupHelper.driver.findElement(By.linkText("home page")).click();
  }

  public void confirmAlert() {
    //   assertThat(driver.switchTo().alert().getText(), is("Delete 1 addresses?"));
    groupHelper.driver.switchTo().alert().accept();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }
}
