package pft.tests;

import org.testng.ITestListener;
import org.testng.ITestResult;
import pft.appmanager.ApplicationManager;

public class MyTestListener implements ITestListener {

  @Override
  public void onTestFailure(ITestResult result) {
    ApplicationManager app = (ApplicationManager) result.getTestContext().getAttribute("app");
    app.takeScreenshot();
  }

}
