package pft.tests;

import org.testng.annotations.Test;
import pft.model.ContactData;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase{

  @Test
  public void testContactPhone() {
    app.goTo().homePage();
    ContactData contact = app.contact().getAll().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

  }

  private String mergePhones(ContactData contact) {
    return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .filter(s -> ! s.equals(""))
            .map(ContactPhoneTest::clear)
            .collect(Collectors.joining("\n"));
  }

  private static String clear(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
