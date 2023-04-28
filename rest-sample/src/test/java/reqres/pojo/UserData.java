package reqres.pojo;

import lombok.*;

@Data
public class UserData {
  private Integer id;
  private String email;
  private String first_name;
  private String last_name;
  private String avatar;
}