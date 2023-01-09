package pft.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.model.ContactData;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactEditionTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().getAll().size() == 0) {
      app.contact().create(new ContactData("Juliett", "Suslenkova", "Corporation", null, "89567845736",null, "group5"));
    }
  }

  @Test
  public void testContactEdition() {
    Set<ContactData> before = app.contact().getAll();
    ContactData editedContact = before.iterator().next();
    ContactData newContactData = new ContactData(editedContact.getId(), "Margo", "Frolova", "SCB", "Academ", "88888888888","mf749@gtkd,ru", null);
    app.contact().edit(newContactData);
    Set<ContactData> after = app.contact().getAll();
    Assert.assertEquals(after.size(), before.size());
    before.remove(editedContact);
    before.add(newContactData);
    Assert.assertEquals(before,after);
  }

}
