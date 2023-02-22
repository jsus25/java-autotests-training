package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminHelper extends HelperBase {
  private final String baseUrl = app.getProperty("web.baseUrl");

  public AdminHelper(ApplicationManager app) {super(app);}

  public void login() {
    driver.get(baseUrl + "/login_page.php");
    type(By.name("username"), app.getProperty("web.adminLogin"));
    type(By.name("password"), app.getProperty("web.adminPass"));
    click(By.cssSelector("input[type = 'submit']"));
  }

  public void goTo(String link) {
    driver.get(baseUrl + String.format("/%s.php", link));
  }

  public void manageUser(String user) {
    click(By.linkText(user));
  }

  public void resetPassword() {
    driver.findElement(By.id("manage-user-reset-form")).findElement(By.className("button")).click();
//    click(By.cssSelector("input[value = 'Сбросить пароль']"));
  }

  public void logout() {
    click(By.id("logout-link"));
  }
}
