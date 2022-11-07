package pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pft.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void deleteContact() {
    click(By.cssSelector(".left:nth-child(8) > input"));
  }
  public void submitContactCreation() {
    click(By.name("submit"));
  }


  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.first_name());
    type(By.name("lastname"),contactData.last_name());
    type(By.name("company"),contactData.company());
    type(By.name("address"),contactData.address());
    type(By.name("mobile"),contactData.mobile_phone());
    type(By.name("email"),contactData.email());
    if (creation) {
      new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.group());
    }
  }
  public void selectElement() {
    click(By.name("selected[]"));
  }

  public void confirmAlert() {
    //   assertThat(driver.switchTo().alert().getText(), is("Delete 1 addresses?"));
    driver.switchTo().alert().accept();
  }
  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void initEdition() {
    click(By.cssSelector(("tr:nth-child(2) > .center:nth-child(8) img")));
  }

  public void submitContactUpdate() { click(By.name("update"));
  }
}
