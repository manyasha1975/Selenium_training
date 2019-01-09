package ru.mytest.litecart.appmanager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;


public class ApplicationManager {

  private final Properties properties;
  public EventFiringWebDriver driver;
  public SessionHelper sessionHelper;
  public NavigationHelper navigationHelper;
  public ProductHelper productHelper;

  public boolean acceptNextAlert = true;
  public String baseUrl;
  public StringBuffer verificationErrors = new StringBuffer();
  private String browser;
  public WebDriverWait wait;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public static class MyListener extends AbstractWebDriverEventListener {
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
      //System.out.println(by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
      //System.out.println(by + " found");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
      System.out.println(throwable);
      File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      File screen = new File("screen-" + System.currentTimeMillis() + ".png");
      try {
        Files.copy(tmp, screen);
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println(screen);
    }
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    if (browser.equals(BrowserType.FIREFOX)) {
      driver = new EventFiringWebDriver(new FirefoxDriver());
      driver.register(new MyListener());
    } else if (browser.equals(BrowserType.CHROME)) {
      driver = new EventFiringWebDriver(new ChromeDriver());
      driver.register(new MyListener());
    } else if (browser.equals(BrowserType.IE)) {
      driver = new EventFiringWebDriver(new InternetExplorerDriver());
      driver.register(new MyListener());
    }
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 5);
    sessionHelper = new SessionHelper(this);
    navigationHelper = new NavigationHelper(this);
    productHelper = new ProductHelper(this);
    driver.get(properties.getProperty("web.baseUrl"));
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public void stop() {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  public SessionHelper session() {
    return sessionHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ProductHelper product() {
    return productHelper;
  }

}
