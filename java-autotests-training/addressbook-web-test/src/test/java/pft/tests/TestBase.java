package pft.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pft.appmanager.ApplicationManager;

public class TestBase {

  protected final ApplicationManager app = new ApplicationManager();

  @BeforeMethod
  public void setUp() {
    app.init();
  }

  @AfterMethod
  public void tearDown() {
    app.stop();
  }

}
