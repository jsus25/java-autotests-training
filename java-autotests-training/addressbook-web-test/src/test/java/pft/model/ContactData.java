package pft.model;

import com.google.gson.annotations.Expose;

import java.io.File;
import java.util.Objects;

public final class ContactData {
  private int id;
  @Expose  //для библиотеки gson, чтобы игнорировать при сериализации все поля кроме помеченных этой аннотацией
  private final String firstName;
  @Expose
  private final String lastName;
  @Expose
  private final String company;
  @Expose
  private final String address;
  @Expose
  private final String homePhone;
  @Expose
  private final String mobilePhone;
  @Expose
  private final String workPhone;
  @Expose
  private final String email;
  @Expose
  private String email2;
  @Expose
  private String email3;
  @Expose
  private final String group;
  private transient File photo;  //игнорировать при сериализации
  private String allPhones;
  private String allEmails;

  public ContactData(String firstName, String lastName, String company, String address, String homePhone, String mobilePhone, String workPhone, String email, String group) {
    this.homePhone = homePhone;
    this.workPhone = workPhone;
    this.id = Integer.MAX_VALUE;
    this.firstName = firstName;
    this.lastName = lastName;
    this.company = company;
    this.address = address;
    this.mobilePhone = mobilePhone;
    this.email = email;
    this.group = group;
  }

  public ContactData(int id, String firstName, String lastName, String company, String address, String homePhone, String mobilePhone, String workPhone, String email, String email2, String email3, String group) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.company = company;
    this.address = address;
    this.homePhone = homePhone;
    this.mobilePhone = mobilePhone;
    this.workPhone = workPhone;
    this.email = email;
    this.email2 = email2;
    this.email3 = email3;
    this.group = group;
  }

  public int getId() {
    return id;
  }
  public String getFirstname() {return firstName;}
  public String getLastname() {
    return lastName;
  }
  public String getCompany() {
    return company;
  }
  public String getAddress() {return address;}
  public String getMobilePhone() {
    return mobilePhone;
  }
  public String getEmail() {return email;}
  public String getEmail2() {
    return email2;
  }
  public String getEmail3() {
    return email3;
  }
  public String getGroup() {
    return group;
  }
  public String getHomePhone() {
    return homePhone;
  }
  public String getWorkPhone() {
    return workPhone;
  }
  public File getPhoto() {return photo;}


  public String getAllPhones() {
    return allPhones;
  }
  public String getAllEmails() {return allEmails;}

  public void setId(int id) {
    this.id = id;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public void setAllPhones(String allPhones) {
    this.allPhones = allPhones;
  }

  public void setAllEmails(String allEmails) {
    this.allEmails = allEmails;
  }


  @Override
  public String toString() {
    return "ContactData[" +
            "first_name=" + firstName + ", " +
            "last_name=" + lastName + ", " +
            "company=" + company + ", " +
            "address=" + address + ", " +
            "mobile_phone=" + mobilePhone + ", " +
            "email=" + email + ", " +
            "group=" + group + ']';
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName);
  }
}