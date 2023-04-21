package reqres.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserRequestData {
  private String name;
  private String job;

  public UpdateUserRequestData() {

  }
}
