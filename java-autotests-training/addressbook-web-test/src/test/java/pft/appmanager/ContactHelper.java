package pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pft.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void deleteContact() {
    click(By.cssSelector(".left:nth-child(8) > input"));

 //   driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.first_name());
    type(By.name("lastname"),contactData.last_name());
    type(By.name("company"),contactData.company());
    type(By.name("address"),contactData.address());
    type(By.name("mobile"),contactData.mobile_phone());
    type(By.name("email"),contactData.email());

//    driver.findElement(By.name("firstname")).sendKeys(contactData.first_name());
//    driver.findElement(By.name("lastname")).sendKeys(contactData.last_name());
//    driver.findElement(By.name("company")).sendKeys(contactData.company());
//    driver.findElement(By.name("address")).sendKeys(contactData.address());
//    driver.findElement(By.name("mobile")).sendKeys(contactData.mobile_phone());
//    driver.findElement(By.name("email")).sendKeys(contactData.email());
  }

  public void confirmAlert() {
    //   assertThat(driver.switchTo().alert().getText(), is("Delete 1 addresses?"));
    driver.switchTo().alert().accept();
  }
}
