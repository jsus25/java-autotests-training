package pft.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pft.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", "CHROME"));
                                                                 //EDGE, FIREFOX, CHROME

  @BeforeSuite
  public void setUp() throws IOException {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
