package pft.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class GroupEditionTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData(null,"group5", "h5", "f5"));
    }
  }
  @Test
  public void testGroupEdition() {
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int index = before.size() - 1;
    GroupData newGroup = new GroupData(before.get(index).id(), "group7", "gh10", "gf10");
    app.getGroupHelper().editGroup(index, newGroup);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(newGroup);
    Comparator<? super GroupData> byID = Comparator.comparingInt(GroupData::id);
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);
  }

}

