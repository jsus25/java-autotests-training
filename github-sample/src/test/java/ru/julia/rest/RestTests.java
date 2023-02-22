package ru.julia.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests {
  private final String baseUrl = "https://team-vtr1.testit.software/api";


  @Test
  public void testCreateTestCase() throws IOException {
    String projectId = "613914d3-c73d-4506-92f7-34bfe315f69b";
    Set<TestCase> oldTestCases = getTestCases(projectId);
    TestCase newTestCase = new TestCase().withName("Automation Added Case");
    int testCaseId = createTestCase();
    Set<TestCase> newTestCases = getTestCases(projectId);
    oldTestCases.add(newTestCase.withId(testCaseId));
    assertEquals(newTestCases, oldTestCases);

  }

  private Set<TestCase> getTestCases(String projectId) throws IOException {

    String body = Request.Get(baseUrl + String.format("/v2/projects/%s/workItems?isDeleted=false&includeIterations=true",projectId))
            .addHeader("authorization","PrivateToken bXk4anUzeVBNOFR1Mm5PbVlp")
            .execute().returnContent().asString();
    return new Gson().fromJson(body, new TypeToken<Set<TestCase>>(){}.getType());
  }

  private int createTestCase() throws IOException {
    String json = Request.Post(baseUrl + "/v2/workItems")
            .addHeader("authorization","PrivateToken bXk4anUzeVBNOFR1Mm5PbVlp")
            .bodyString("""
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
                    }""", ContentType.APPLICATION_JSON)
            .execute()
            .returnContent()
            .asString();

        return new Gson().fromJson(json, TestCase.class).getGlobalId();

  }

}
