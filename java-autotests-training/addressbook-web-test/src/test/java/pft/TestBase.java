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

  protected void selectGroup() {
    driver.findElement(By.name("selected[]")).click();
  }
}
