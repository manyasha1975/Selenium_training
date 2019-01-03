package ru.mytest.litecart.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration() {
    long now = System.currentTimeMillis();
    String firstname = String.format("Boris", now);
    String lastname = "Ivanov";
    String address1 = "Lenina av.";
    String postcode = "12345";
    String city = "Boston";
    String country = "United States";
    String state = "California";
    String email = String.format("boris%s@localhost.localdomain", now);
    String phone = "+19101234567";
    String password = "password";
    app.driver.findElement(By.cssSelector("a[href='http://localhost/litecart/en/create_account']")).click();
    app.driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys(firstname);
    app.driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys(lastname);
    app.driver.findElement(By.cssSelector("input[name='address1']")).sendKeys(address1);
    app.driver.findElement(By.cssSelector("input[name='postcode']")).sendKeys(postcode);
    app.driver.findElement(By.cssSelector("input[name='city']")).sendKeys(city);
    app.driver.findElement(By.cssSelector("span.select2-selection__arrow")).click();
    app.driver.findElement(By.cssSelector("span.select2-search.select2-search--dropdown > input"))
            .sendKeys(country  + Keys.ENTER);
    new Select(app.driver.findElement(By.cssSelector("select[name='zone_code']"))).selectByVisibleText(state);
    app.driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
    app.driver.findElement(By.cssSelector("input[name='phone']")).sendKeys(phone);
    app.driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
    app.driver.findElement(By.cssSelector("input[name='confirmed_password']")).sendKeys(password);
    app.driver.findElement(By.cssSelector("button[name='create_account']")).click();
    app.driver.findElement(By.cssSelector("a[href='http://localhost/litecart/en/logout']")).click();
    app.driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
    app.driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
    app.driver.findElement(By.cssSelector("button[name='login']")).click();
    app.driver.findElement(By.cssSelector("a[href='http://localhost/litecart/en/logout']")).click();
  }
}
