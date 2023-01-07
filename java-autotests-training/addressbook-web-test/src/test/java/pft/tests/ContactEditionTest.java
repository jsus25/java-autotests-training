package pft.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactEditionTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Juliett", "Suslenkova", "Corporation", null, "89567845736",null, "group5"));
    }
  }

  @Test
  public void testContactEdition() {
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    ContactData newContactData = new ContactData(before.get(index).getId(), "Margo", "Frolova", "SCB", "Academ", "88888888888","mf749@gtkd,ru", null);
    app.getContactHelper().editContact(index, newContactData);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(newContactData);
    Comparator<? super ContactData> ByID = Comparator.comparingInt(ContactData::getId);
    before.sort(ByID);
    after.sort(ByID);
    Assert.assertEquals(before,after);
  }

}
