package ru.mytest.litecart.appmanager;

import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class SessionHelper extends HelperBase {

  public SessionHelper(ApplicationManager app) {
    super(app);
  }

  public void loginAs(String username, String password) {
    if (username.equals("admin")) {
      app.goTo().adminPage();
      type(By.name("username"), username);
      type(By.name("password"), password);
      click(By.name("login"));
      waitTitle("My Store");
    } else {
      type(By.cssSelector("input[name='email']"), username);
      type(By.cssSelector("input[name='password']"), password);
      click(By.cssSelector("button[name='login']"));
      waitTitle("Online Store | My Store");
    }
  }

  public void logout() {
    click(By.cssSelector("a[href *= logout]"));
  }

  public void waitTitle(String text) {
    app.wait.until(titleIs(text));
  }
}
