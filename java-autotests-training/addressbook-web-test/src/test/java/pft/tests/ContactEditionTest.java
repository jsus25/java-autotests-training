package pft.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.model.ContactData;
import pft.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEditionTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().getAll().size() == 0) {
      app.contact().create(new ContactData("Juliett", "Suslenkova", "Corporation", null, null, "89567845736", null, null, "group5"));
    }
  }

  @Test
  public void testContactEdition() {
    Contacts before = app.contact().getAll();
    ContactData editedContact = before.iterator().next();
    ContactData newContactData = new ContactData(editedContact.getId(), "Margo", "Frolova", "SCB", "Academ", null, "88888888888", null, "mf749@gtkd,ru", null);
    app.contact().edit(newContactData);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().getAll();
    assertThat(after, equalTo(before.without(editedContact).withAdded(newContactData)));
  }

}
