package ru.mytest.litecart.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.BrowserType;
import ru.mytest.litecart.appmanager.ApplicationManager;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @Before
  public void setUp() throws Exception {
    app.init();
  }

  @After
  public void tearDown() throws Exception {
    app.stop();
  }

}
