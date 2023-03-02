package pft.appmanager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

public class ApplicationManager {
  private final Properties properties;
  protected WebDriver driver;
  private NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;
  private DbHelper dbHelper;
  JavascriptExecutor js;
  private final String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));

    dbHelper = new DbHelper();

    if ("".equals(properties.getProperty("selenium.server"))) {
      if (Objects.equals(browser, "chrome")) {
        driver = new ChromeDriver();
      } else if (Objects.equals(browser, "firefox")) {
        driver = new FirefoxDriver();
      } else if (Objects.equals(browser, "edge")) {
        driver = new EdgeDriver();
      }
    } else {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setBrowserName(browser);
      capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "win10")));
      driver = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
    }



    js = (JavascriptExecutor) driver;
    driver.get(properties.getProperty("web.baseUrl"));
    driver.manage().window().setSize(new Dimension(1198, 804));
    groupHelper = new GroupHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    contactHelper = new ContactHelper(driver, navigationHelper);
    SessionHelper sessionHelper = new SessionHelper(driver);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPass"));
  }

  public void stop() {
    driver.quit();
  }
  public GroupHelper group() {
    return groupHelper;
  }
  public ContactHelper contact() {
    return contactHelper;
  }
  public NavigationHelper goTo() {
    return navigationHelper;
  }
  public DbHelper db() { return dbHelper; }
}
