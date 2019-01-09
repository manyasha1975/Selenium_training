package ru.mytest.litecart.tests;

import org.junit.Test;

public class LoginAdminTest extends TestBase{

  @Test
  public void testLoginAdmin() {
    app.session().loginAs("admin", "admin");
    app.session().logout();
  }
}
