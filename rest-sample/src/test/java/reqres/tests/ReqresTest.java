package reqres.tests;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import reqres.pojo.UserData;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ReqresTest {
  private static final String URL = "https://reqres.in/";

  @Test
  public void checkAvatarAndIdTest() {

    List<UserData> users = given()
            .when()
              .contentType(ContentType.JSON)
              .get(URL + "api/users?page=2")
            .then()
              .log().all()
              .extract().body().jsonPath().getList("data", UserData.class);  //path - путь или ключ в json файле, из значения которого извлекать объекты. Если в корне, то путь указывать "."
 //   Assert.assertTrue(users.stream().allMatch(u -> u.getAvatar().contains((u.getId().toString()))));
    users.forEach(u -> Assert.assertTrue(u.getAvatar().contains(u.getId().toString())));

    int i=0;

  }
}
