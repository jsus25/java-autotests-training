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
  private final NavigationHelper goTo;

  public ContactHelper(WebDriver driver, NavigationHelper goTo) {
    super(driver);
    this.goTo = goTo;
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
    attach(By.name("photo"),contactData.getPhoto());
    type(By.name("company"),contactData.getCompany());
    type(By.name("address"),contactData.getAddress());
    type(By.name("mobile"),contactData.getMobilePhone());
    type(By.name("work"),contactData.getWorkPhone());
    type(By.name("email"),contactData.getEmail());
    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertEquals(contactData.getGroups().size(), 1);
        new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().name());
      }
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
    goTo.contactAddPage();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void edit(ContactData newContactData) {
    initEditionById(newContactData.getId());
    fillContactForm(newContactData, false);
    submitContactUpdate();
    contactCache = null;
    goTo.homePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    confirmAlert();
    contactCache = null;
    goTo.homePage();
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
      String allEmails = element.findElement(By.xpath("td[5]")).getText();
      String allPhones = element.findElement(By.xpath("td[6]")).getText();
      int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("id"));
      ContactData contact = new ContactData(id, firstName, lastName, null, null,
              null, null,null, null, null, null);
      contact.setAllPhones(allPhones);
      contact.setAllEmails(allEmails);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact){
     initEditionById(contact.getId());
     String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
     String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
     String company = driver.findElement(By.name("company")).getAttribute("value");
     String address = driver.findElement(By.name("address")).getAttribute("value");
     String homePhone = driver.findElement(By.name("home")).getAttribute("value");
     String mobilePhone = driver.findElement(By.name("mobile")).getAttribute("value");
     String workPhone = driver.findElement(By.name("work")).getAttribute("value");
     String email = driver.findElement(By.name("email")).getAttribute("value");
     String email2 = driver.findElement(By.name("email2")).getAttribute("value");
     String email3 = driver.findElement(By.name("email3")).getAttribute("value");
     return new ContactData(contact.getId(), firstname, lastname, company, address,
             homePhone, mobilePhone, workPhone, email, email2, email3);}

  public String contentFromInfoPage(ContactData contact) {
    goTo.homePage();
    driver.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", contact.getId()))).click();
    return driver.findElement(By.id("content")).getText();
  }
}
