package pft;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
  JavascriptExecutor js;
  private WebDriver driver;

  @BeforeMethod
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    driver.get("http://localhost/addressbook/");
    driver.manage().window().setSize(new Dimension(1198, 804));
    login("admin", "secret");
  }

  private void login(String username, String password) {
    driver.findElement(By.name("user")).sendKeys(username);
    driver.findElement(By.name("pass")).sendKeys(password);
    driver.findElement(By.cssSelector("input:nth-child(7)")).click();
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }

  protected void submitGroupCreation() {
    driver.findElement(By.name("submit")).click();
  }

  protected void fillGroupForm(GroupData groupData) {
    driver.findElement(By.name("group_name")).sendKeys(groupData.name());
    driver.findElement(By.name("group_header")).sendKeys(groupData.header());
    driver.findElement(By.name("group_footer")).sendKeys(groupData.footer());
  }

  protected void initGroupCreation() {
    driver.findElement(By.name("new")).click();
  }

  protected void goToGroupPage() {
    driver.findElement(By.linkText("groups")).click();
  }

  protected void returnToGroupPage() {
    driver.findElement(By.linkText("group page")).click();
  }

  protected void deleteGroup() {
    driver.findElement(By.name("delete")).click();
  }

  protected void deleteContact() {
    driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
  }

  protected void selectElement() {
    driver.findElement(By.name("selected[]")).click();
  }

  protected void submitContactCreation() {
    driver.findElement(By.name("submit")).click();
  }

  protected void fillContactForm(ContactData contactData) {
    driver.findElement(By.name("firstname")).sendKeys(contactData.first_name());
    driver.findElement(By.name("lastname")).sendKeys(contactData.last_name());
    driver.findElement(By.name("company")).sendKeys(contactData.company());
    driver.findElement(By.name("address")).sendKeys(contactData.address());
    driver.findElement(By.name("mobile")).sendKeys(contactData.mobile_phone());
    driver.findElement(By.name("email")).sendKeys(contactData.email());
  }

  protected void goToContactAddPage() {
    driver.findElement(By.linkText("add new")).click();
  }
  protected void goToHomePage() {
    driver.findElement(By.linkText("home")).click();
  }
  protected void returnToHomePage() {
    driver.findElement(By.linkText("home page")).click();
  }

  protected void confirmAlert() {
    //   assertThat(driver.switchTo().alert().getText(), is("Delete 1 addresses?"));
    driver.switchTo().alert().accept();
  }

}
