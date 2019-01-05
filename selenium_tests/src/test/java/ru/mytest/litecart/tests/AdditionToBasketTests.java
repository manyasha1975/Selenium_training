package ru.mytest.litecart.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class AdditionToBasketTests extends TestBase {

  @Test
  public void testAdditionToBasket() {
    //addition to cart
    for (int i = 0; i < 3; i++) {
      List<WebElement> ducks = app.driver.findElements(By.cssSelector("div.content .products li"));
      ducks.get(0).findElement(By.cssSelector("a.link")).click();
      String quantity = app.driver.findElement(By.cssSelector("div#cart .quantity"))
              .getAttribute("textContent");
      String newQuantity = Integer.toString(Integer.parseInt(quantity) + 1);
      List<WebElement> select = app.driver.findElements(By.cssSelector("select"));
      if (select.size() > 0) {
        new Select(app.driver.findElement(By.cssSelector("select[name='options[Size]']")))
                .selectByVisibleText("Small");
      }
      app.driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
      app.wait.until(textToBe(By.cssSelector("div#cart .quantity"), newQuantity));
      app.driver.findElement(By.cssSelector("a[href='http://localhost/litecart/en/']")).click();
    }
    //deletion from cart
    app.driver.findElement(By.cssSelector("a[href='http://localhost/litecart/en/checkout']")).click();
    List<WebElement> quantity = app.driver.findElements(By.cssSelector("table.dataTable tr td.item"));
    Integer number = quantity.size();
    for (int i = 0; i < quantity.size(); i++) {
      app.driver.findElement(By.cssSelector("button[name='remove_cart_item']")).click();
      app.wait.until(numberOfElementsToBe(By.cssSelector("table.dataTable tr td.item"), number - 1));
      number = number - 1;
    }
    String noProducts = app.driver.findElement(By.cssSelector("div#checkout-cart-wrapper em"))
            .getAttribute("textContent");
    Assert.assertTrue(noProducts.equals("There are no items in your cart."));
    app.driver.findElement(By.cssSelector("div#checkout-cart-wrapper a")).click();
  }
}
