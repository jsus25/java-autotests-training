package pft.tests;

import org.testng.annotations.Test;
import pft.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends TestBase{

  @Test
  public void testContactEmail() {
    app.goTo().homePage();
    ContactData contact = app.contact().getAll().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

  }

  private String mergeEmails(ContactData contact) {
    return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .filter(s -> ! s.equals(""))
            .map(ContactEmailTest::clear)
            .collect(Collectors.joining("\n"));
  }
  private static String clear(String phone) {
    return phone.replaceAll("\\s", "");
  }


}
