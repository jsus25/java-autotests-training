package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws IOException {
    long now = System.currentTimeMillis();
    String user = "user1";
    String newPassword = String.format("pass%s",now);
    app.admin().login();
    app.admin().goTo("manage_user_page");
    app.admin().manageUser(user);
    app.admin().resetPassword();
    app.admin().logout();

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String resetLink = app.mail().findLink(mailMessages, user + "@localhost.localdomain");
    app.registration().finish(resetLink, newPassword);

    assertTrue(app.newSession().login(user, newPassword));

  }


  @AfterMethod(alwaysRun = true)  //чтобы срабатывал даже тогда, когда тест завершился неуспешно
  public void stopMailServer() {
    app.mail().stop();
  }
}
