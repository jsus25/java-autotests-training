package reqres.tests;

import org.testng.annotations.BeforeSuite;
import reqres.Specifications;

public class TestBase {
  @BeforeSuite
  public void init() {
    Specifications.installSpecification("https://reqres.in/");
  }
}
