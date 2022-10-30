package pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pft.model.ContactData;

public class ContactHelper {
  private WebDriver driver;

  public ContactHelper(WebDriver driver) {
    this.driver = driver;
  }

  public void deleteContact() {
    driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
  }

  public void fillContactForm(ContactData contactData) {
    driver.findElement(By.name("firstname")).sendKeys(contactData.first_name());
    driver.findElement(By.name("lastname")).sendKeys(contactData.last_name());
    driver.findElement(By.name("company")).sendKeys(contactData.company());
    driver.findElement(By.name("address")).sendKeys(contactData.address());
    driver.findElement(By.name("mobile")).sendKeys(contactData.mobile_phone());
    driver.findElement(By.name("email")).sendKeys(contactData.email());
  }

  public void confirmAlert() {
    //   assertThat(driver.switchTo().alert().getText(), is("Delete 1 addresses?"));
    driver.switchTo().alert().accept();
  }
}
