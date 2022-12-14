package pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pft.model.ContactData;
import pft.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {
  private final NavigationHelper navigationHelper;

  public ContactHelper(WebDriver driver, NavigationHelper navigationHelper) {
    super(driver);
    this.navigationHelper = navigationHelper;
  }

  private void deleteContact() {
    click(By.cssSelector(".left:nth-child(8) > input"));
  }
  private void submitContactCreation() {
    click(By.name("submit"));
  }

  private void fillContactForm(ContactData contactData, boolean creation) {
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

  private void selectContactById(int id) {
    driver.findElement(By.cssSelector("input[id='" + id + "']")).click();
  }

  private void confirmAlert() {
    //   assertThat(driver.switchTo().alert().getText(), is("Delete 1 addresses?"));
    driver.switchTo().alert().accept();
  }
  private void returnToHomePage() {
    click(By.linkText("home page"));
  }

  private void initEditionById(int id) {
    driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  private void submitContactUpdate() { click(By.name("update"));
  }

  public int count() {
    return driver.findElements(By.name("entry")).size();
  }

  public void create(ContactData contact) {
    navigationHelper.contactAddPage();
    if (isGroupPresent(contact.getGroup())) {
      fillContactForm(contact, true);
      submitContactCreation();
      contactCache = null;
    } else {
      System.out.println("!!! Couldn't create a contact: Group is absent in DB");
    }
    returnToHomePage();
  }

  public void edit(ContactData newContactData) {
    initEditionById(newContactData.getId());
    fillContactForm(newContactData, false);
    submitContactUpdate();
    contactCache = null;
    navigationHelper.homePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    confirmAlert();
    contactCache = null;
    navigationHelper.homePage();
  }

  private boolean isGroupPresent(String group) {
    try {
      new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(group);
      return true;
    } catch (NoSuchElementException exception) {
      return false;
    }
  }

  private Contacts contactCache = null;

  public Contacts getAll() {
     if (contactCache != null) {
       return new Contacts(contactCache);
     }
    contactCache = new Contacts();
    List<WebElement> elements = driver.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String lastName = element.findElement(By.xpath("td[2]")).getText();
      String firstName = element.findElement(By.xpath("td[3]")).getText();
      String allPhones = element.findElement(By.xpath("td[6]")).getText();
      int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("id"));
      ContactData contact = new ContactData(id, firstName, lastName, null, null, null, null, null, null, null);
      contact.setAllPhones(allPhones);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact){
     initEditionById(contact.getId());
     String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
     String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
     String homePhone = driver.findElement(By.name("home")).getAttribute("value");
     String mobilePhone = driver.findElement(By.name("mobile")).getAttribute("value");
     String workPhone = driver.findElement(By.name("work")).getAttribute("value");
     return new ContactData(contact.getId(), firstname, lastname, null, null, homePhone, mobilePhone, workPhone, null, null);}
}
