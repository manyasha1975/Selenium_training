package ru.mytest.litecart.tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class NewWindowsVerificationTests extends TestBase {

  @Before
  public void loginAdmin() {
    app.driver.get("http://localhost/litecart/admin/");
    app.driver.findElement(By.name("username")).sendKeys("admin");
    app.driver.findElement(By.name("password")).sendKeys("admin");
    app.driver.findElement(By.name("login")).click();
    app.wait.until(titleIs("My Store"));
  }

  @Test
  public void testNewWindowsVerification() {
    app.driver.findElement(By.cssSelector("a[href='http://localhost/litecart/admin/?app=countries&doc=countries']")).click();
    List<WebElement> countries = app.driver.findElements(By.cssSelector("table.dataTable tr.row"));
    WebElement editedCountry = countries.iterator().next();
    editedCountry.findElement(By.cssSelector("a")).click();
    List<WebElement> allLinks = app.driver.findElements(By.cssSelector("table tr td > a"));
    List<WebElement> links = new ArrayList<WebElement>();
    for (WebElement link : allLinks) {
      String l = link.getAttribute("href");
      if (!l.contains("#")) {
        links.add(link);
        System.out.println(l);
      }
    }
    for (WebElement link : links) {
      String originalWindow = app.driver.getWindowHandle();
      Set<String> existingWindows = app.driver.getWindowHandles();
      link.click();
      String newWindow = app.wait.until(anyWindowOtherThan(existingWindows));
      app.driver.switchTo().window(newWindow);
      app.driver.close();
      app.driver.switchTo().window(originalWindow);
    }
  }

  private ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
    return new ExpectedCondition<String>() {
      public String apply(WebDriver driver) {
        Set<String> handles = driver.getWindowHandles();
        handles.removeAll(oldWindows);
        return handles.size() > 0 ? handles.iterator().next():null;
      }
    };
  }
}
