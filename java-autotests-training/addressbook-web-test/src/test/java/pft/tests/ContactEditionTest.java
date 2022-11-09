package pft.tests;

import org.testng.annotations.Test;
import pft.model.ContactData;

public class ContactEditionTest extends TestBase{

  @Test
  public void testContactEdition() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Juliett", "Suslenkova", "Corporation", null, "89567845736",null, "group5"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initEdition();
    app.getContactHelper().fillContactForm(new ContactData("Margo", "Frolova", "SCB", "Academ", "88888888888","mf749@gtkd,ru", null), false);
    app.getContactHelper().submitContactUpdate();
    app.getNavigationHelper().goToHomePage();
  }

}
