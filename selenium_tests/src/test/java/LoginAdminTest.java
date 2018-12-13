import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class LoginAdminTest {

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
    System.out.println(((HasCapabilities) driver).getCapabilities());
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 10);
  }

  @Test
  public void testLoginAdmin() {
    driver.get("http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).submit();
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }
}
