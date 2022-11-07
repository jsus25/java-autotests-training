package pft.model;

import java.util.Objects;

public final class ContactData {
  private final String firstName;
  private final String lastName;
  private final String company;
  private final String address;
  private final String mobilePhone;
  private final String email;
  private final String group;

  public ContactData(String firstName, String lastName, String company, String address, String mobilePhone, String email, String group) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.company = company;
    this.address = address;
    this.mobilePhone = mobilePhone;
    this.email = email;
    this.group = group;
  }


  public String getFirstname() {
    return firstName;
  }
  public String getLastname() {
    return lastName;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (ContactData) obj;
    return Objects.equals(this.firstName, that.firstName) &&
            Objects.equals(this.lastName, that.lastName) &&
            Objects.equals(this.company, that.company) &&
            Objects.equals(this.address, that.address) &&
            Objects.equals(this.mobilePhone, that.mobilePhone) &&
            Objects.equals(this.email, that.email) &&
            Objects.equals(this.group, that.group);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, company, address, mobilePhone, email, group);
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

}