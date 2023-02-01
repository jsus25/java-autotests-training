package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class ApplicationManager {
  private final Properties properties;
  protected WebDriver driver;
//  private NavigationHelper navigationHelper;

  JavascriptExecutor js;
  private final String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));

    if (Objects.equals(browser, "CHROME")) {
      driver = new ChromeDriver();
    } else if (Objects.equals(browser, "FIREFOX")) {
      driver = new FirefoxDriver();
    } else if (Objects.equals(browser, "EDGE")) {
      driver = new EdgeDriver();
    }

    js = (JavascriptExecutor) driver;
    driver.get(properties.getProperty("web.baseUrl"));
    driver.manage().window().setSize(new Dimension(1198, 804));
    //    navigationHelper = new NavigationHelper(driver);
 //   SessionHelper sessionHelper = new SessionHelper(driver);
  //  sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPass"));
  }

  public void stop() {
    driver.quit();
  }

//  public NavigationHelper goTo() {   return navigationHelper; }

}
