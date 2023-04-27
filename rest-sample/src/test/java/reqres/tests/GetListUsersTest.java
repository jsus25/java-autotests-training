package reqres.tests;

/*
Задание:
 1. Используя сервис https://reqres.in/ получить список пользователей со второй(2) страницы
 2. Убедиться, что в именах файлов-аватаров пользователей указан их айдишник
 3. Убедиться, что email пользователей имеет окончание reqres.in

 Используется:
   - извлечение из тела ответа списка объектов pojo класса
   - проверка значений полей через stream API
  */

import org.testng.Assert;
import org.testng.annotations.Test;
import reqres.Specifications;
import reqres.pojo.UserData;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetListUsersTest extends TestBase {

  @Test
  public void checkAvatarAndIdTest() {
    Specifications.updateSpecification(200);
    List<UserData> users = given()
            .when()
              .get("api/users?page=2")
            .then()
              .log().all()
              .extract().body().jsonPath().getList("data", UserData.class);                            //path - путь или ключ в json файле, из значения которого извлекать объекты. Если в корне, то путь указывать "."
    Assert.assertTrue(users.stream().allMatch(u -> u.getAvatar().contains(u.getId().toString())));
    //users.forEach(u -> Assert.assertTrue(u.getAvatar().contains(u.getId().toString()))); - то же самое, что и выше
    Assert.assertTrue(users.stream().allMatch(u -> u.getEmail().endsWith("reqres.in")));
  }

}
