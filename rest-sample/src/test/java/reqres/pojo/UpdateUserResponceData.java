package reqres.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserResponceData extends UpdateUserRequestData {
  private String updatedAt;
}
