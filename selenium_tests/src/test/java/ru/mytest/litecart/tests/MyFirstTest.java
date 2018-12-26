package ru.mytest.litecart.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {

  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start(){
    //run firefox in old mode
    //DesiredCapabilities caps = new DesiredCapabilities();
    //caps.setCapability(FirefoxDriver.MARIONETTE, false);
    //if you want to use non-system folder for driver, set user folder with driver
    //System.setProperty("webdriver.chrome.driver", "D:/Tools/chromedriver.exe");
    driver = new ChromeDriver();
    //driver = new FirefoxDriver(new FirefoxBinary(new File("D:\\Tools\\ESR\\firefox.exe")),
    //        new FirefoxProfile(), caps);
    //driver = new InternetExplorerDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 10);
  }

  @Test
  public void testMyFirstTest() {
    driver.get("http://google.com");
    driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[1]/div/div[2]/div[1]/span")).click();
    driver.findElement(By.id("K32")).click();
    driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[1]/div/div[2]/div[1]/span")).click();
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElement(By.name("btnK")).submit();
    wait.until(titleIs("webdriver - Поиск в Google"));
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }
}
