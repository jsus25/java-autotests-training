package pft.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.model.ContactData;
import pft.model.Contacts;
import pft.model.GroupData;
import pft.model.Groups;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactRemoveFromGroupTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
  }

  @Test
  public void testContactRemoveFromGroup() {
    GroupData group = groupWithAliveContacts();
    Contacts groupContactsBefore = group.getContacts();
    Contacts contacts = new Contacts(groupContactsBefore);
    contacts.retainAll(app.db().contacts());   // пересечение boolean
    ContactData contact = contacts.iterator().next();

    app.goTo().homePage();
    app.contact().filter(group);
    app.contact().selectContactById(contact.getId());
    app.contact().removeFromGroup();

    Contacts groupContactsAfter = app.db().groups().stream()
            .filter(g -> (Objects.equals(g.id(), group.id())))
            .iterator().next()
            .getContacts();
    groupContactsAfter.retainAll(app.db().contacts());

    assertThat(groupContactsAfter, equalTo(groupContactsBefore.without(contact)));
  }

  private GroupData groupWithAliveContacts() {
    Groups result = new Groups();
    Groups groups = app.db().groups();
    for (GroupData group : groups) {
      Contacts contacts = group.getContacts();
      contacts.retainAll(app.db().contacts());
      group.setContacts(contacts);
      result = result.withAdded(group);
    }
    return result.stream().filter(g -> g.getContacts().size() > 0).iterator().next();
  }

}
