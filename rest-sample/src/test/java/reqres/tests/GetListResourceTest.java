package reqres.tests;
/*
Убедиться, что операция LIST<RESOURCE> возвращает данные, отсортированные по годам.
Используется:
- извлечение данных из тела запроса в pojo класс
  - маппинг списка объектов на список значений одного из полей
  - сортировка списка через stream
  - сравнение списков
 */

import org.testng.Assert;
import org.testng.annotations.Test;
import reqres.Specifications;
import reqres.pojo.ResourceData;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetListResourceTest extends TestBase {

  @Test
  public void checkSorting() {
    Specifications.updateSpecification(200);
    List<ResourceData> resources = given()
            .when()
            .get("api/unknown")
            .then()
            .log().all()
            .extract().body().jsonPath().getList("data", ResourceData.class);  //path - путь или ключ в json файле, из значения которого извлекать объекты. Если в корне, то путь указывать "."
    List<Integer> years = resources.stream().map(ResourceData::getYear).toList();
    List<Integer> sortedYears = years.stream().sorted().toList();
    Assert.assertEquals(years, sortedYears);

  }
}