package pft.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pft.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class GroupEditionTest extends TestBase{


  @Test
  public void testGroupEdition() {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData(null,"group5", "h5", "f5"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectElement(before.size() - 1);
    app.getGroupHelper().initEdition();
    GroupData newGroup = new GroupData(before.get(before.size() - 1).id(), "group7", "gh10", "gf10");
    app.getGroupHelper().fillGroupForm(newGroup);
    app.getGroupHelper().submitGroupUpdate();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(newGroup);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}

