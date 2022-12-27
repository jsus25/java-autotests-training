package pft.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pft.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactEditionTest extends TestBase{

  @Test
  public void testContactEdition() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Juliett", "Suslenkova", "Corporation", null, "89567845736",null, "group5"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initEdition(before.size() - 1);
    ContactData newContactData = new ContactData(before.get(before.size() - 1).getId(), "Margo", "Frolova", "SCB", "Academ", "88888888888","mf749@gtkd,ru", null);
    app.getContactHelper().fillContactForm(newContactData, false);
    app.getContactHelper().submitContactUpdate();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(newContactData);
    Comparator<? super ContactData> ByID = Comparator.comparingInt(ContactData::getId);
    before.sort(ByID);
    after.sort(ByID);
    Assert.assertEquals(before,after);
  }
}
