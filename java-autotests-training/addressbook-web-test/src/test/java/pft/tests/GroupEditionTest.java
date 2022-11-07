package pft.tests;

import org.testng.annotations.Test;
import pft.model.GroupData;


public class GroupEditionTest extends TestBase{


  @Test
  public void testGroupEdition() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectElement();
    app.getGroupHelper().initEdition();
    app.getGroupHelper().fillGroupForm(new GroupData("groop7", "gh10", "gf10"));
    app.getGroupHelper().submitGroupUpdate();
    app.getGroupHelper().returnToGroupPage();
  }

}

