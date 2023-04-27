package testit.deprecated;

import org.testng.annotations.BeforeSuite;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBaseDeprecated {
  private final Properties properties = new Properties();
  protected String baseUrl;
  protected String projectId;
  protected String token;

  @BeforeSuite
  public void init() throws IOException {
    properties.load(new FileReader("src/test/resources/local.properties"));
    baseUrl = properties.getProperty("baseUrl");
    projectId = properties.getProperty("projectId");
    token = properties.getProperty("token");
  }
}
