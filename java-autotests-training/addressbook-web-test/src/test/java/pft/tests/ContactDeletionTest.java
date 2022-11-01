package pft.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectElement();
    app.getContactHelper().deleteContact();
    app.getContactHelper().confirmAlert();
    app.getNavigationHelper().goToHomePage();
  }

 }
