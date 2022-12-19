package pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pft.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver driver) {
    super(driver);
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.name());
    type(By.name("group_header"), groupData.header());
    type(By.name("group_footer"), groupData.footer());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }
  public void clickDelete() {
    click(By.name("delete"));
  }
  public void initEdition() {click(By.name("edit"));
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }


  public void selectElement(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();
  }


  public void submitGroupUpdate() { click(By.name("update"));
  }

  public boolean isThereAGroup() {
    return (isElementPresent(By.name("selected[]")));
  }

  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public List<GroupData> getGroupList() {
    List<GroupData> groups = new ArrayList<>();
    List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      GroupData group = new GroupData(name, null, null);
      groups.add(group);
    }
    return groups;
  }
}
