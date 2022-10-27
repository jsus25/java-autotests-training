package pft;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase{

  @Test
  public void testContactCreation() {
    goToHomePage();
    selectElement();
    deleteContact();
    confirmAlert();
    goToHomePage();
  }

 }
