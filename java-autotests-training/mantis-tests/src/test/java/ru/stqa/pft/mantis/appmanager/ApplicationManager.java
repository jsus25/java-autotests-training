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
  private WebDriver driver;
  private final String browser;
  JavascriptExecutor js;
  private RegistrationHelper registrationHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
  }

  public void stop() {
    if (driver != null) {
      driver.quit();
    }
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public WebDriver getDriver() {
    if (driver == null) {
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
    }
    return driver;
  }
}
