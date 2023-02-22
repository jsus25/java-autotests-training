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
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData("Juliett", "Suslenkova", "Corporation", null, null, "89567845736", null, null));
    }
  }

  @Test
  public void testContactEdition() {
    Contacts before = app.db().contacts();
    ContactData editedContact = before.iterator().next();
    ContactData newContactData = new ContactData(editedContact.getId(), "Margo", "Frolova", "SCB", "Academ",
            "", "88888888888", "", "mf749@gtkd,ru", "", "");
    app.contact().edit(newContactData);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    Contacts exRes = before.without(editedContact).withAdded(newContactData);   // для отладки, убрать потом
    assertThat(after, equalTo(before.without(editedContact).withAdded(newContactData)));
  }

}
