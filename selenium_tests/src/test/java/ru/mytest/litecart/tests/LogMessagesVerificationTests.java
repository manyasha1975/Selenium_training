package ru.mytest.litecart.tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class LogMessagesVerificationTests extends TestBase {

  @Before
  public void loginAdmin() {
    app.session().loginAs("admin", "admin");
  }

  @Test
  public void testLogMessagesVerification() {
    System.out.println(app.driver.manage().logs().getAvailableLogTypes());
    app.driver.findElement(By.cssSelector("a[href='http://localhost/litecart/admin/?app=catalog&doc=catalog']")).click();
    app.driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
    app.driver.findElement(By
            .cssSelector("a[href='http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1']")).click();
    app.driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
    List<WebElement> quantity = app.driver.findElements(By.cssSelector("table.dataTable tr td > input[name *= products]"));
    for (int i = 0; i < quantity.size(); i++) {
      List<WebElement> products = app.driver.findElements(By.cssSelector("table.dataTable tr.row"));
      List<WebElement> productList = new ArrayList<WebElement>();
      for (WebElement product : products) {
        List<WebElement> columns = product.findElements(By.cssSelector("td"));
        String nameProduct = columns.get(2).getAttribute("textContent");
        if (!nameProduct.equals(" [Root]") & !nameProduct.equals(" Rubber Ducks") & !nameProduct.equals(" Subcategory")) {
          productList.add(product.findElement(By.cssSelector("td > a[title='Edit']")));
        }
      }
      productList.get(i).click();
      app.driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
      app.driver.findElement(By.cssSelector("a[href='http://localhost/litecart/admin/?app=catalog&doc=catalog']")).click();
      app.driver.findElement(By
              .cssSelector("a[href='http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1']")).click();
    }
  }
}
