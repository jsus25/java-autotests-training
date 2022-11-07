package pft.model;

import java.util.Objects;

public final class ContactData {
  private final String first_name;
  private final String last_name;
  private final String company;
  private final String address;
  private final String mobile_phone;
  private final String email;
  private final String group;

  public ContactData(String first_name, String last_name, String company, String address, String mobile_phone, String email, String group) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.company = company;
    this.address = address;
    this.mobile_phone = mobile_phone;
    this.email = email;
    this.group = group;
  }

  public String first_name() {
    return first_name;
  }

  public String last_name() {
    return last_name;
  }

  public String company() {
    return company;
  }

  public String address() {
    return address;
  }

  public String mobile_phone() {
    return mobile_phone;
  }

  public String email() {
    return email;
  }

  public String group() {
    return group;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (ContactData) obj;
    return Objects.equals(this.first_name, that.first_name) &&
            Objects.equals(this.last_name, that.last_name) &&
            Objects.equals(this.company, that.company) &&
            Objects.equals(this.address, that.address) &&
            Objects.equals(this.mobile_phone, that.mobile_phone) &&
            Objects.equals(this.email, that.email) &&
            Objects.equals(this.group, that.group);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first_name, last_name, company, address, mobile_phone, email, group);
  }

  @Override
  public String toString() {
    return "ContactData[" +
            "first_name=" + first_name + ", " +
            "last_name=" + last_name + ", " +
            "company=" + company + ", " +
            "address=" + address + ", " +
            "mobile_phone=" + mobile_phone + ", " +
            "email=" + email + ", " +
            "group=" + group + ']';
  }

}