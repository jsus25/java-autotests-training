package pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pft.model.GroupData;
import pft.model.Groups;
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

  private void selectElementById(int id) {
    driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void submitGroupUpdate() { click(By.name("update"));
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }
  public void edit(GroupData group) {
    selectElementById(group.id());
    initEdition();
    fillGroupForm(group);
    submitGroupUpdate();
    returnToGroupPage();
  }

  public void delete(GroupData group) {
    selectElementById(group.id());
    clickDelete();
    returnToGroupPage();
  }

  public Groups getAll() {
    Groups groups = new Groups();
    List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      Integer id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData(id, name, null, null);
      groups.add(group);
    }
    return groups;
  }

}
