package pft.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pft.appmanager.ApplicationManager;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager("CHROME");
  //EDGE, FIREFOX, CHROME

  @BeforeSuite
  public void setUp() {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
