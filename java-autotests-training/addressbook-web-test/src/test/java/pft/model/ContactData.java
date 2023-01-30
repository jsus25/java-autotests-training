package pft.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public final class ContactData {
  @Id
  @Column
  private int id;
  @Expose  //для библиотеки gson, чтобы игнорировать при сериализации все поля кроме помеченных этой аннотацией
  @Column
  private String firstName = "";
  @Expose
  @Column
  private String lastName = "";
  @Expose
  private String company = "";
  @Expose
  @Type(type = "text")
  private String address = "";
  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone = "";
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone = "";
  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone = "";
  @Expose
  @Type(type = "text")
  private String email = "";
  @Expose
  @Type(type = "text")
  private String email2 = "";
  @Expose
  @Type(type = "text")
  private String email3 = "";
  @Column
  @Type(type = "text")
  @Transient
  private String photo;
  @Transient
  private String allPhones;
  @Transient
  private String allEmails;
  @ManyToMany(fetch = FetchType.EAGER)  //опция fetch (по умолчанию - Lazy - ленивый, извлекать из БД минимум инфрмации. Eager - жадный, извлекать максимум, чтобы можно было закрыть сессию с БД пораньше)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<>();

  public ContactData(String firstName, String lastName, String company, String address, String homePhone, String mobilePhone, String workPhone, String email) {
    this.homePhone = homePhone;
    this.workPhone = workPhone;
    this.id = Integer.MAX_VALUE;
    this.firstName = firstName;
    this.lastName = lastName;
    this.company = company;
    this.address = address;
    this.mobilePhone = mobilePhone;
    this.email = email;
    //    this.group = group;
  }

  public ContactData(int id, String firstName, String lastName, String company, String address, String homePhone, String mobilePhone, String workPhone, String email, String email2, String email3) {
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
    //this.group = group;
  }

  public ContactData() {
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
  public Groups getGroups() {
    return new Groups(groups);
  }
  public String getHomePhone() {
    return homePhone;
  }
  public String getWorkPhone() {
    return workPhone;
  }
  public String getPhoto() { return photo;}

  public String getAllPhones() {
    return allPhones;
  }
  public String getAllEmails() {return allEmails;}

  public void setId(int id) {
    this.id = id;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public void setAllPhones(String allPhones) {
    this.allPhones = allPhones;
  }

  public void setAllEmails(String allEmails) {
    this.allEmails = allEmails;
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }


  @Override
  public String toString() {
    return "ContactData[" +
            "id=" + id + ", " +
            "first_name=" + firstName + ", " +
            "last_name=" + lastName + ", " +
            "company=" + company + ", " +
            "address=" + address + ", " +
            "mobile_phone=" + mobilePhone + ", " +
            "email=" + email + ']';
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(firstName, that.firstName)
            && Objects.equals(lastName, that.lastName) && Objects.equals(company, that.company)
            && Objects.equals(address, that.address) && Objects.equals(homePhone, that.homePhone)
            && Objects.equals(mobilePhone, that.mobilePhone) && Objects.equals(workPhone, that.workPhone)
            && Objects.equals(email, that.email) && Objects.equals(email2, that.email2)
            && Objects.equals(email3, that.email3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, company, address, homePhone, mobilePhone, workPhone, email, email2, email3);
  }
}