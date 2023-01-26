package pft.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity                           //для hibernate
@Table(name = "group_list")          //для hibernate
public final class GroupData {
  @Id                             //для hibernate
  @Column(name = "group_id")      //для hibernate
  private Integer id;
  @Column(name = "group_name")
  private String name;
  @Column(name = "group_header")
  @Type(type = "text")              //для hibernate
  private String header;
  @Column(name = "group_footer")
  @Type(type = "text")              //для hibernate
  private String footer;

  public GroupData(Integer id, String name, String header, String footer) {
    this.id = id;
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public GroupData() {

  }

  public Integer id() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return Objects.equals(id, groupData.id) && Objects.equals(name, groupData.name) && Objects.equals(header, groupData.header) && Objects.equals(footer, groupData.footer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, header, footer);
  }

  public String name() {
    return name;
  }

  public String header() {
    return header;
  }

  public String footer() {
    return footer;
  }

  @Override
  public String toString() {
    return "GroupData[" +
            "id=" + id + ", " +
            "name=" + name + ", " +
            "header=" + header + ", " +
            "footer=" + footer + ']';
  }

}
