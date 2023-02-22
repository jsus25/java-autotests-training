package pft.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pft.model.ContactData;
import pft.model.GroupData;
import pft.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddToGroupTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
  }

  @Test
  public void testContactAddToGroup() {
    Groups  dbGroup = app.db().groups();
    ContactData contact = contactWithoutAllGroups(dbGroup);    //выбрали произвольный контакт из базы, который не во все группы добавлен
    Groups contactGroupsBefore = contact.getGroups();    //запомнили его список групп
    GroupData group = freeGroup(dbGroup, contactGroupsBefore);   // выбрали группу, в которую будем добавлять

    app.goTo().homePage();
    app.contact().selectContactById(contact.getId());
    app.contact().addToGroup(group);
    Groups contactGroupsAfter = app.db().contacts().stream()
            .filter(c -> c.getId() == contact.getId())
            .findFirst().get()
            .getGroups();
    assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.withAdded(group)));
  }

  private ContactData contactWithoutAllGroups(Groups dbGroup) {
    return app.db().contacts().stream()
            .filter(c -> c.getGroups().size() < dbGroup.size())
            .iterator().next();    //выбрали произвольный контакт из базы, который не во все группы добавлен
  }

  private GroupData freeGroup(Groups dbGroup, Groups contactGroups) {
    if (contactGroups.size() > 0) {
      dbGroup.removeAll(contactGroups);
    }
    return dbGroup.iterator().next();   // выбрали группу, в которую будем добавлять

  }
}
