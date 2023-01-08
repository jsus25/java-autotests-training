package pft.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().getList().size() == 0) {
      app.contact().create(new ContactData("Juliett", "Suslenkova", "Corporation", null, "89567845736",null, "group5"));
    }
  }

  @Test
  public void testContactDeletion() {
    List<ContactData> before = app.contact().getList();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().getList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(index);
    Assert.assertEquals(before, after);
  }

}
