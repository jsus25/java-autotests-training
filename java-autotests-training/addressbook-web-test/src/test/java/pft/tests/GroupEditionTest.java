package pft.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pft.model.GroupData;


public class GroupEditionTest extends TestBase{


  @Test
  public void testGroupEdition() {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("group5", "h5", "f5"));
    }
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectElement(before - 1);
    app.getGroupHelper().initEdition();
    app.getGroupHelper().fillGroupForm(new GroupData("group7", "gh10", "gf10"));
    app.getGroupHelper().submitGroupUpdate();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);
  }

}

