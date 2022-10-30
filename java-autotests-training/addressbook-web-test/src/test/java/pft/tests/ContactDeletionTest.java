package pft.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.goToHomePage();
    app.selectElement();
    app.getContactHelper().deleteContact();
    app.getContactHelper().confirmAlert();
    app.goToHomePage();
  }

 }
