package reqres.tests;

/*
Задача:
1. Используя сервис https://reqres.in/ протестированть регистрацию пользователя в системе
2. Необходимо создание двух тестов:
     - успешная регистрация
     - регистрация с ошибкой из-за отсутствия пароля
3. Проверить коды ошибок

Используется:
 - builder вместо конструктора
 - формирование тела запроса из pojo класса
 - извлечение одиночного объекта из тела ответа
 - проверка полей в извлеченном объекте
 - проверка значения поля в ответе без извлечения в объект (unsuccessRegTest)
 */

import org.testng.Assert;
import org.testng.annotations.Test;
import reqres.Specifications;
import reqres.pojo.RegisterRequestData;
import reqres.pojo.RegisterResponseData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationTests extends  TestBase {
  @Test
  public void successRegTest() {
    Specifications.updateSpecification(200);
    String email = "eve.holt@reqres.in";
    String password = "pistol";
    Integer id = 4;
    String token = "QpwL5tke4Pnpja7X4";

    RegisterRequestData user = RegisterRequestData.builder().email(email).password(password).build();
    RegisterResponseData response = given().body(user)
            .when().post("api/register")
            .then().log().all()
               .extract().body().as(RegisterResponseData.class);
    Assert.assertNotNull(response.getId());
    Assert.assertNotNull(response.getToken());

    Assert.assertEquals(response.getId(), id);
    Assert.assertEquals(response.getToken(), token);
  }

  @Test
  public void unsuccessRegTest() {
    String email = "sydney@fife";
    Specifications.updateSpecification(400);
    RegisterRequestData user = RegisterRequestData.builder().email(email).build();
    given().body(user)
            .when().post("api/register")
            .then()
              .log().all()
              .body("error", equalTo("Missing password"));
  }
}
