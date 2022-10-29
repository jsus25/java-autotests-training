package pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pft.model.GroupData;

public class GroupHelper {
  protected WebDriver driver;

  public void submitGroupCreation() {
    driver.findElement(By.name("submit")).click();
  }

  public void fillGroupForm(GroupData groupData) {
    driver.findElement(By.name("group_name")).sendKeys(groupData.name());
    driver.findElement(By.name("group_header")).sendKeys(groupData.header());
    driver.findElement(By.name("group_footer")).sendKeys(groupData.footer());
  }

  public void initGroupCreation() {
    driver.findElement(By.name("new")).click();
  }

  public void returnToGroupPage() {
    driver.findElement(By.linkText("group page")).click();
  }

  public void deleteGroup() {
    driver.findElement(By.name("delete")).click();
  }
}
