package reqres.tests;

/*
Обновить информацию о пользователе и сравнить время обновления с текущим временем на компьюторе (https://reqres.in/)

Используется:
  - работа с текущим временем
  - регулярные выражения
  - формирование и извлечение тела запроса через pojo классы
 */

import org.testng.Assert;
import org.testng.annotations.Test;
import reqres.Specifications;
import reqres.pojo.UpdateUserRequestData;
import reqres.pojo.UpdateUserResponseData;
import java.time.Clock;

import static io.restassured.RestAssured.given;

public class UpdateUserTest extends TestBase {
  @Test
  public static void checkDates() {
    Specifications.updateSpecification(200);
    String name = "morpheus";
    String job = "zion resident";
    String regex = "(.{5})$";                                                      //регулярное выражение: . - любые символы, {5} - 5 штук, $ - с конца
    String currentTime = Clock.systemUTC().instant().toString()
            .replaceAll("(.{11})$", "");                           //убрали 11 последних символов, чтобы устранить погрешность
    UpdateUserRequestData user = new UpdateUserRequestData(name, job);
    UpdateUserResponseData updatedUser = given().body(user)
            .when().put("api/users/2")
            .then().log().all()
              .extract().body().as(UpdateUserResponseData.class);
    String updatedTime = updatedUser.getUpdatedAt()
            .replaceAll(regex, "");                                      //убрали последние 5 символов
    Assert.assertEquals(updatedTime, currentTime);
  }

}
