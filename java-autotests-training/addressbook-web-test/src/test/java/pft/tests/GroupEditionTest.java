package pft.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.model.GroupData;
import java.util.Set;


public class GroupEditionTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().getAll().size() == 0) {
      app.group().create(new GroupData(null,"group5", "h5", "f5"));
    }
  }
  @Test
  public void testGroupEdition() {
    Set<GroupData> before = app.group().getAll();
    GroupData editedGroup = before.iterator().next(); //возвращает произвольный элемент массива (группу)
    GroupData group = new GroupData(editedGroup.id(), "group7", "gh10", "gf10");
    app.group().edit(group);
    Set<GroupData> after = app.group().getAll();
    Assert.assertEquals(after.size(), before.size());

    before.remove(editedGroup);
    before.add(group);
    Assert.assertEquals(before, after);
  }

}

