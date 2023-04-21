package reqres;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

public class Specifications {
  public static void installSpecification(String url, Integer responseCode) {
    RestAssured.requestSpecification = new RequestSpecBuilder()
            .setBaseUri(url)
            .setContentType(ContentType.JSON)
            .build();
    RestAssured.responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(responseCode)
            .build();
  }


}
