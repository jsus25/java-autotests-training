package pft.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.model.ContactData;
import pft.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Contacts before = app.contact().getAll();
    ContactData editedContact = before.iterator().next();
    ContactData newContactData = new ContactData(editedContact.getId(), "Margo", "Frolova", "SCB", "Academ", "88888888888","mf749@gtkd,ru", null);
    app.contact().edit(newContactData);
    Contacts after = app.contact().getAll();
    assertThat(after.size(), equalTo(before.size()));
     assertThat(after, equalTo(before.without(editedContact).withAdded(newContactData)));
  }

}
