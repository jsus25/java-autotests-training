package pft.tests;

// !!! Assertion is specially broken for beautiful reports in Allure



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.model.GroupData;
import pft.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData(null,"group5", "h5", "f5"));
    }
  }

  @Test
  public void testGroupDeletion() {
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next(); //возвращает произвольный элемент массива (группу)
    app.group().delete(deletedGroup);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(deletedGroup)));
  }

}
