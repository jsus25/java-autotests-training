package pft.tests;// Generated by Selenium IDE

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupCreation5() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectElement();
    app.getGroupHelper().clickDelete();
    app.getGroupHelper().returnToGroupPage();
  }

}
