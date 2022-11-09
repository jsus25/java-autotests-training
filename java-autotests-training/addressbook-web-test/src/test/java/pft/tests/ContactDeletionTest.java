package pft.tests;

import org.testng.annotations.Test;
import pft.model.ContactData;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Juliett", "Suslenkova", "Corporation", null, "89567845736",null, "group9"));
    }
    app.getContactHelper().selectElement();
    app.getContactHelper().deleteContact();
    app.getContactHelper().confirmAlert();
    app.getNavigationHelper().goToHomePage();
  }

 }
