package reqres.tests;
/*
Проверить удаление второго пользователя и сравнить статус-код
 */

import org.testng.annotations.Test;
import reqres.Specifications;

import static io.restassured.RestAssured.given;

public class DeleteUserTest extends TestBase {

  @Test
  public static void deleteUser() {
    Specifications.updateSpecification(204);
    given()
            .when().delete("api/users/2")
            .then().log().all();
  }
}
