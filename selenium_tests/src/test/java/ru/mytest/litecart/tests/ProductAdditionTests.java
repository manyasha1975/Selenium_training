package ru.mytest.litecart.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class ProductAdditionTests extends TestBase {

  @Before
  public void loginAdmin() {
    app.driver.get("http://localhost/litecart/admin/");
    app.driver.findElement(By.name("username")).sendKeys("admin");
    app.driver.findElement(By.name("password")).sendKeys("admin");
    app.driver.findElement(By.name("login")).click();
    app.wait.until(titleIs("My Store"));
  }

  @Test
  public void testProductAddition() throws InterruptedException {
    String name = "Hockey Duck";
    String code = "RD0025";
    File photo = new File("src/test/resources/duck_hockey.jpg");
    app.driver.findElement(By.cssSelector("a[href='http://localhost/litecart/admin/?app=catalog&doc=catalog']")).click();
    app.driver.findElement(By
            .cssSelector("a[href='http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product']")).click();
    //General tab
    List<WebElement> status = app.driver.findElements(By.cssSelector("input[name='status']"));
    status.get(0).click();
    app.driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys(name);
    app.driver.findElement(By.cssSelector("input[name='code']")).sendKeys(code);
    app.driver.findElement(By.cssSelector("input[data-name='Root']")).click();
    app.driver.findElement(By.cssSelector("input[data-name='Rubber Ducks']")).click();
    List<WebElement> gender = app.driver.findElements(By.cssSelector("input[name='product_groups[]']"));
    gender.get(1).click();
    WebElement quantity = app.driver.findElement(By.cssSelector("input[name='quantity']"));
    quantity.clear();
    quantity.sendKeys("15");
    app.driver.findElement(By.cssSelector("input[name='new_images[]']")).sendKeys(photo.getAbsolutePath());
    app.driver.findElement(By.cssSelector("input[name='date_valid_from']")).sendKeys("01/01/2019");
    app.driver.findElement(By.cssSelector("input[name='date_valid_to']")).sendKeys("02/01/2019");

    //Information tab
    app.driver.findElement(By.cssSelector("a[href='#tab-information']")).click();
    new Select(app.driver.findElement(By.cssSelector("select[name='manufacturer_id']"))).selectByVisibleText("ACME Corp.");
    app.driver.findElement(By.cssSelector("input[name='keywords']")).sendKeys("hockey, duck, sport");
    app.driver.findElement(By.cssSelector("input[name='short_description[en]']"))
            .sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sollicitudin " +
                    "ante massa.");
    app.driver.findElement(By.cssSelector("textarea[name='description[en]']"))
            .sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sollicitudin " +
                    "ante massa, eget ornare libero porta congue. Cras scelerisque dui non consequat sollicitudin. Sed " +
                    "pretium tortor ac auctor molestie. Nulla facilisi. Maecenas pulvinar nibh vitae lectus vehicula" +
                    " semper. Donec et aliquet velit. Curabitur non ullamcorper mauris. In hac habitasse platea dictumst." +
                    " Phasellus ut pretium justo, sit amet bibendum urna. Maecenas sit amet arcu pulvinar, facilisis quam" +
                    " at, viverra nisi. Morbi sit amet adipiscing ante. Integer imperdiet volutpat ante, sed venenatis" +
                    " urna volutpat a. Proin justo massa, convallis vitae consectetur sit amet, facilisis id libero.");

    //Price tab
    app.driver.findElement(By.cssSelector("a[href='#tab-prices']")).click();
    app.driver.findElement(By.cssSelector("input[name='purchase_price']")).clear();
    app.driver.findElement(By.cssSelector("input[name='purchase_price']")).sendKeys("10.00");
    new Select(app.driver.findElement(By.cssSelector("select[name='purchase_price_currency_code']")))
            .selectByVisibleText("US Dollars");
    app.driver.findElement(By.cssSelector("input[name='prices[USD]']")).sendKeys("18.0000");
    app.driver.findElement(By.cssSelector("input[name='prices[EUR]']")).sendKeys("0.0000");
    app.driver.findElement(By.cssSelector("button[name='save']")).click();

    //verify that product was added
    app.driver.findElement(By.cssSelector("a[href='http://localhost/litecart/admin/?app=catalog&doc=catalog']")).click();
    app.driver.findElement(By
            .cssSelector("a[href='http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1']")).click();
    List<WebElement> products = app.driver.findElements(By.cssSelector("table.dataTable tr.row"));
    List<String> productList = new ArrayList<String>();
    for (WebElement product : products) {
      List<WebElement> columns = product.findElements(By.cssSelector("td"));
      String nameProduct = columns.get(2).getAttribute("textContent");
      if (!nameProduct.equals(" [Root]")&!nameProduct.equals(" Rubber Ducks")&!nameProduct.equals(" Subcategory")) {
        productList.add(nameProduct);
        System.out.println(nameProduct);
      }
    }
    Assert.assertTrue(productList.contains(" " + name));
  }
}
