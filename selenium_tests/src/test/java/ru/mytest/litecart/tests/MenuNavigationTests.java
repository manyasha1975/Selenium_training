package ru.mytest.litecart.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MenuNavigationTests extends TestBase {

  @Before
  public void loginAdmin() {
    app.driver.get("http://localhost/litecart/admin/");
    app.driver.findElement(By.name("username")).sendKeys("admin");
    app.driver.findElement(By.name("password")).sendKeys("admin");
    app.driver.findElement(By.name("login")).click();
    app.wait.until(titleIs("My Store"));
  }

  @Test
  public void testMenuNavigation() {
    List<WebElement> elements = app.driver.findElements(By.cssSelector("#app- > a"));
    List<String> links = new ArrayList<>();
    for (WebElement element : elements) {
      links.add(element.getAttribute("href"));
    }
    for (String link : links) {
      WebElement element = app.driver.findElement(By.cssSelector("a[href='" + link + "']"));
      element.click();
      List<WebElement> itemsSubMenu = app.driver.findElements(By.cssSelector("li#app-.selected > ul.docs > li > a"));
      List<String> items = new ArrayList<>();
      for (WebElement item : itemsSubMenu) {
        System.out.println("1. Items of submenu: " + item.getAttribute("href"));
        items.add(item.getAttribute("href"));
      }
      if (items.size() > 0) {
        for (String item : items) {
          WebElement subMenu = app.driver.findElement(By.cssSelector("a[href='" + item + "']"));
          subMenu.click();
          WebElement header = app.driver.findElement(By.tagName("h1"));
          Assert.assertNotNull(header);
          System.out.println("2. Header: " + header.getText());
        }
      } else {
        WebElement header = app.driver.findElement(By.tagName("h1"));
        Assert.assertNotNull(header);
        System.out.println("2. Header: " + header.getText());
      }
    }
  }
}
