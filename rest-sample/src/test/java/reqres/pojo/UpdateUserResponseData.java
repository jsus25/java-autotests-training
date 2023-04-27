package reqres.pojo;

import lombok.Data;

@Data
public class UpdateUserResponseData {
  private String name;
  private String job;
  private String updatedAt;
}
