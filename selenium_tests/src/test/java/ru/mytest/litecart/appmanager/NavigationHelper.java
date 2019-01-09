package ru.mytest.litecart.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void adminPage() {
    driver.get("http://localhost/litecart/admin/");
  }

  public void homePage() {
    click(By.cssSelector("a[href='http://localhost/litecart/en/']"));
  }

  public void cartPage() {
    click(By.cssSelector("a[href='http://localhost/litecart/en/checkout']"));
  }

  public void returnFromCart() {
    click(By.cssSelector("div#checkout-cart-wrapper a"));
  }

}
