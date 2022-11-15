package pft.tests;// Generated by Selenium IDE

import org.testng.Assert;
import org.testng.annotations.Test;
import pft.model.GroupData;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupCreation5() {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("group5", "h5", "f5"));
    }
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectElement();
    app.getGroupHelper().clickDelete();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before - 1);
  }

}
