package pft.tests;

import org.testng.annotations.Test;
import pft.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTest extends TestBase{

  @Test
  public void testContactInfo() {
    app.goTo().homePage();
    ContactData contact = app.contact().getAll().iterator().next();  //выбрали какой-то контакт
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(app.contact().contentFromInfoPage(contact).replaceAll("\n\n\nMember of: group5", ""),
            equalTo(mergeAll(contactInfoFromEditForm)));
  }

  private String mergeAll(ContactData contact) {
    return Stream.of(mergeNames(contact), contact.getCompany().trim(), contact.getAddress().trim(), mergePhones(contact), mergeEmails(contact))
            .filter(s -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  private String mergeNames(ContactData contact) {
    return Stream.of(contact.getFirstname(), contact.getLastname())
            .filter(s -> ! s.equals(""))
            .map(String::trim)                                  //у всех элементов списка обрезать пробелы в начале и конце
            .collect(Collectors.joining(" "));
  }

  private String mergeEmails(ContactData contact) {
  String merge = Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
          .filter(s -> ! s.equals(""))
          .map(String::trim)
          .collect(Collectors.joining("\n"));
    if (!merge.equals("")) {
      merge = "\n" + merge;
    }
    return merge;
  }

  private String mergePhones(ContactData contact) {
    String merge = Stream.of(normalizePhone(contact.getHomePhone(), "H: "), normalizePhone(contact.getMobilePhone(), "M: "), normalizePhone(contact.getWorkPhone(), "W: "))
            .filter(s -> ! s.equals(""))
            .map(String::trim)
            .collect(Collectors.joining("\n"));
    if (!merge.equals("")) {
      merge = "\n" + merge;
    }
    return merge;
  }

  private String normalizePhone(String phone, String letter){
    if (!phone.equals("")) {
      phone = letter + phone;
    }
    return phone;
  }

}