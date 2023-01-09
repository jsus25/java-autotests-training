package pft.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.model.GroupData;
import pft.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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
    Groups before = app.group().getAll();
    GroupData editedGroup = before.iterator().next(); //возвращает произвольный элемент массива (группу)
    GroupData group = new GroupData(editedGroup.id(), "group7", "gh10", "gf10");
    app.group().edit(group);
    Groups after = app.group().getAll();
    Assert.assertEquals(after.size(), before.size());
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before.without(editedGroup).withAdded(group)));
  }

}

