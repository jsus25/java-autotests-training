package pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pft.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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
  public void selectContact(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();
  }

  public void confirmAlert() {
    //   assertThat(driver.switchTo().alert().getText(), is("Delete 1 addresses?"));
    driver.switchTo().alert().accept();
  }
  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void initEdition(int index) {
    driver.findElements(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr/td[8]")).get(index).click();
  }

  public void submitContactUpdate() { click(By.name("update"));
  }
  public void createContact(ContactData contact) {
    navigationHelper.goToContactAddPage();
    if (isGroupPresent(contact.getGroup())) {
      fillContactForm(contact, true);
      submitContactCreation();
    } else {
      System.out.println("!!! Couldn't create a contact: Group is absent in DB");
    }
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return (isElementPresent(By.name("selected[]")));
  }
  public boolean isGroupPresent(String group) {
    try {
      new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(group);
      return true;
    } catch (NoSuchElementException exception) {
      return false;
    }
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = driver.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String lastName = element.findElement(By.xpath("td[2]")).getText();
      String firstName = element.findElement(By.xpath("td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("id"));
      ContactData contact = new ContactData(id, firstName, lastName, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
