package pft.tests;// Generated by Selenium IDE

import org.testng.Assert;
import org.testng.annotations.Test;
import pft.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    List<ContactData> before = app.contact().getList();
    ContactData newContactData = new ContactData("Juliett", "Suslenkova", "Corporation", null, "89567845736",null, "group5");
    app.contact().create(newContactData);
    List<ContactData> after = app.contact().getList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(newContactData);
    Comparator<? super ContactData> ByID = Comparator.comparingInt(ContactData::getId);
    before.sort(ByID);
    after.sort(ByID);
    Assert.assertEquals(before,after);
   }

}
