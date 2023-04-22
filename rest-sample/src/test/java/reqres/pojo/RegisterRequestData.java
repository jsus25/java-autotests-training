package reqres.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequestData {
  private String email;
  private String password;
}
