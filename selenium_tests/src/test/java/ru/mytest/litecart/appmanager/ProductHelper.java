package ru.mytest.litecart.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class ProductHelper extends HelperBase {

  public ProductHelper(ApplicationManager app) {
    super(app);
  }

  public String getQuantity() {
    return app.driver.findElement(By.cssSelector("div#cart .quantity"))
            .getAttribute("textContent");
  }

  public void selectFirstProduct() {
    List<WebElement> ducks = app.driver.findElements(By.cssSelector("div.content .products li"));
    ducks.get(0).findElement(By.cssSelector("a.link")).click();
    List<WebElement> select = app.driver.findElements(By.cssSelector("select"));
    if (select.size() > 0) {
      new Select(app.driver.findElement(By.cssSelector("select[name='options[Size]']")))
              .selectByVisibleText("Small");
    }
  }

  public void addToCart() {
    click(By.cssSelector("button[name='add_cart_product']"));
  }

  public void removeFromCart() {
    click(By.cssSelector("button[name='remove_cart_item']"));
  }

  public void removeAllFromCart() {
    List<WebElement> quantity = app.driver.findElements(By.cssSelector("table.dataTable tr td.item"));
    Integer number = quantity.size();
    for (int i = 0; i < quantity.size(); i++) {
      app.product().removeFromCart();
      app.wait.until(numberOfElementsToBe(By.cssSelector("table.dataTable tr td.item"), number - 1));
      number = number - 1;
    }
  }

  public String cartIsEmpty() {
    return app.driver.findElement(By.cssSelector("div#checkout-cart-wrapper em"))
            .getAttribute("textContent");
  }

  public void waitQuantity(String newQuantity) {
    app.wait.until(textToBe(By.cssSelector("div#cart .quantity"), newQuantity));
  }
}
