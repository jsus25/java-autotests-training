package pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pft.model.ContactData;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    driver.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  private void submitContactUpdate() { click(By.name("update"));
  }
  public void create(ContactData contact) {
    navigationHelper.contactAddPage();
    if (isGroupPresent(contact.getGroup())) {
      fillContactForm(contact, true);
      submitContactCreation();
    } else {
      System.out.println("!!! Couldn't create a contact: Group is absent in DB");
    }
    returnToHomePage();
  }

  public void edit(ContactData newContactData) {
    initEditionById(newContactData.getId());
    fillContactForm(newContactData, false);
    submitContactUpdate();
    navigationHelper.homePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    confirmAlert();
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

   public Set<ContactData> getAll() {
    Set<ContactData> contacts = new HashSet<>();
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
