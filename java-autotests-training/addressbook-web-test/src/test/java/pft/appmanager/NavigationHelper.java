package pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver driver) {
    super(driver);
  }

  public void groupPage() {
    if (isElementPresent(By.name("new")) && driver.findElement(By.tagName("h1")).getText().equals("Groups")) {
      return;
    }
    click(By.linkText("groups"));
  }

  public void contactAddPage() {
    if (isElementPresent(By.linkText("Enter"))) {
      return;
    }
    click(By.linkText("add new"));
  }

  public void homePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }
}
