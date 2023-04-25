package reqres.tests;
/*
Примеры выполнения тестов без применения POJO классов
 */

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import reqres.Specifications;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class WithoutPojoTests {
  private static final String URL = "https://reqres.in/";

  @Test
  public void testGetListUsers() {
    Specifications.installSpecification(URL, 200);
    Response response = given()
            .when().get("api/users?page=2")
            .then()
            .log().all()
            .body("page", equalTo(2))           //проверка, что действительно получили вторую страницу
            .body("data.id",notNullValue())             //путь по дереву json - через точку. Проверка на то, что вообще значения возвращаются непустые
            .body("data.avatar",notNullValue())
            .body("data.email",notNullValue())
            .extract().response();
    JsonPath jsonPath = response.jsonPath();                         //преобазование ответа объект json
    List<Integer> ids = jsonPath.get("data.id");
    List<String> avatars = jsonPath.get("data.avatar");
    List<String> emails = jsonPath.get("data.email");
    for (int i = 0; i < ids.size(); i++ ) {
      Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));    //Проверка, что в именах аватаров содержится id пользователей
    }
    Assert.assertTrue(emails.stream().allMatch(e -> e.endsWith("@reqres.in")));  //Проверка, что email оканчивается на @reqres.in
  }

  @Test
  public void testSuccessUserReg() {
    Specifications.installSpecification(URL, 200);
    Map<String, String> user = new HashMap<>();
    user.put("email", "eve.holt@reqres.in");
    user.put("password", "pistol");
    given().body(user)
            .when().post("api/register")
            .then().log().all()
            .body("id",equalTo(4))                                   //Проверка прямо в теле ответа
            .body("token", equalTo("QpwL5tke4Pnpja7X4"));            //Проверка
  }

  @Test
  public void testUnSuccessUserReg() {
    Specifications.installSpecification(URL, 400);
    Map<String, String> user = new HashMap<>();
    user.put("email", "ydney@fife");
    Response response = given().body(user)
            .when().post("api/register")
            .then().log().all()
            .extract().response();
    JsonPath jsonPath = response.jsonPath();
    Assert.assertEquals(jsonPath.get("error"), "Missing password");        //А тут проверка в извлеченном из ответа json - объекте, хотя можно было бы и в теле проверить

  }
}
