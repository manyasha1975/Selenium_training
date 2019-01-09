package ru.mytest.litecart.tests;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest extends TestBase {

  @Test
  public void getBrowserLogs(){
    app.driver.get("http://selenium2.ru");
    System.out.println(app.driver.manage().logs().getAvailableLogTypes());
    app.driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
    //app.driver.manage().logs().get("performance").forEach(l -> System.out.println(l));
  }

  @Test
  public void testMyFirstTest() {
    app.driver.get("http://google.com");
    app.driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[1]/div/div[2]/div[1]/span")).click();
    app.driver.findElement(By.id("K32")).click();
    app.driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[1]/div/div[2]/div[1]/span")).click();
    app.driver.findElement(By.name("q")).sendKeys("webdriver");
    app.driver.findElement(By.name("btnK")).submit();
    app.wait.until(titleIs("webdriver - Поиск в Google"));
  }

}
