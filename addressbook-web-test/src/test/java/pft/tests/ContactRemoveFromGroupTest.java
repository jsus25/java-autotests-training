package pft.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.model.ContactData;
import pft.model.Contacts;
import pft.model.GroupData;
import pft.model.Groups;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactRemoveFromGroupTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (groupsWithAliveContacts().size() == 0) {
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData(88, "group 88", "header 88", "footer 88"));
      }
      if (app.db().contacts().size() == 0) {
        app.goTo().homePage();
        app.contact().create(new ContactData("Pamella", "Andersen", "Corporation", "", "", "89567845736", "+7(913) 098 54-54", ""));
      }
      app.goTo().homePage();
      app.contact().selectContactById(app.db().contacts().iterator().next().getId());
      app.contact().addToGroup(app.db().groups().iterator().next());
    }
  }

  @Test
  public void testContactRemoveFromGroup() {
    GroupData group = groupsWithAliveContacts().iterator().next();
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

  private Set<GroupData> groupsWithAliveContacts() {
    Groups result = new Groups();
    Groups groups = app.db().groups();
    for (GroupData group : groups) {
      Contacts contacts = group.getContacts();
      contacts.retainAll(app.db().contacts());
      group.setContacts(contacts);
      result = result.withAdded(group);
    }
    return result.stream().filter(g -> g.getContacts().size() > 0).collect(Collectors.toSet());
  }


}
