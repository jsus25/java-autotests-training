package reqres;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

public class Specifications {
  public static void installSpecification(String url) {
    RestAssured.requestSpecification = new RequestSpecBuilder()
            .setBaseUri(url)
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())                          //
            .build();
    RestAssured.responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
  }

  public static void updateSpecification(Integer responseCode) {
    RestAssured.responseSpecification
            .statusCode(responseCode);
  }


}
