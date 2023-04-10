package ru.julia.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase {

  @Test
  public void testCreateTestCase() {
    Set<TestCase> oldTestCases = getTestCases(projectId);
    TestCase newTestCase = new TestCase().withName("Automation Added Case");
    int testCaseId = createTestCase();
    Set<TestCase> newTestCases = getTestCases(projectId);
    oldTestCases.add(newTestCase.withId(testCaseId));
    assertEquals(newTestCases, oldTestCases);

  }

  private Set<TestCase> getTestCases(String projectId) {

    String body = RestAssured
            .given()
            .header("authorization",token)
            .get(baseUrl + String.format("v2/projects/%s/workItems?isDeleted=false&includeIterations=true",projectId))
            .asString();

    return new Gson().fromJson(body, new TypeToken<Set<TestCase>>(){}.getType());
  }

  private int createTestCase() {

    String json = (RestAssured.given().header("authorization", token))
            .body("""
                    {
                      "entityTypeName": "TestCases",
                      "description": "This is a basic test template",
                      "state": "NeedsWork",
                      "priority": "Lowest",
                      "steps": [
                      ],
                      "preconditionSteps": [
                      ],
                      "postconditionSteps": [
                      ],
                      "duration": 10000,
                      "attributes": {
                      },
                      "tags": [
                      ],
                      "attachments": [
                      ],
                      "iterations": [
                      ],
                      "links": [
                      ],
                      "name": "Automation Added Case",
                      "projectId": "613914d3-c73d-4506-92f7-34bfe315f69b",
                      "sectionId": "9e422ec7-e44f-4518-af81-2fb0d6c161b4",
                      "autoTests": [
                      ]
                    }""")
            .contentType("application/json")
            .when()
            .post(baseUrl + "v2/workItems")
            .asString();

        return new Gson().fromJson(json, TestCase.class).getGlobalId();

  }

}
