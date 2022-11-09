package pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pft.model.ContactData;

public class ContactHelper extends HelperBase {
  private final NavigationHelper navigationHelper;

  public ContactHelper(WebDriver driver, NavigationHelper navigationHelper) {
    super(driver);
    this.navigationHelper = navigationHelper;
  }

  public void deleteContact() {
    click(By.cssSelector(".left:nth-child(8) > input"));
  }
  public void submitContactCreation() {
    click(By.name("submit"));
  }


  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("company"),contactData.getCompany());
    type(By.name("address"),contactData.getAddress());
    type(By.name("mobile"),contactData.getMobilePhone());
    type(By.name("email"),contactData.getEmail());
    if (creation) {
      new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
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
  public void createContact(ContactData contact) {
    navigationHelper.goToContactAddPage();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();

  }

  public boolean isThereAContact() {
    return (isElementPresent(By.name("selected[]")));
  }
}
