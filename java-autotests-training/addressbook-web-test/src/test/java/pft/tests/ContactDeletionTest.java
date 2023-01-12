package pft.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.model.ContactData;
import pft.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().getAll().size() == 0) {
      app.contact().create(new ContactData("Juliett", "Suslenkova", "Corporation", null, null, "89567845736", null, null, "group5"));
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.contact().getAll();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().getAll();
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
