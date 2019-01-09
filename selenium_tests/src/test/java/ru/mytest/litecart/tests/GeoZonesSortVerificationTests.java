package ru.mytest.litecart.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeoZonesSortVerificationTests extends TestBase {

  @Before
  public void loginAdmin() {
    app.session().loginAs("admin", "admin");
  }

  @Test
  public void testGeoZonesSortVerification() {
    app.driver.findElement(By.cssSelector("a[href='http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones']")).click();
    List<WebElement> countries = app.driver.findElements(By.cssSelector("table.dataTable tr.row"));
    List<String> countryList = new ArrayList<String>();
    for (WebElement country : countries) {
      String link = country.findElement(By.cssSelector("a")).getAttribute("href");
      countryList.add(link);
    }
    for (String link : countryList) {
      List<String> originGeoZonesList = new ArrayList<String>();
      app.driver.findElement(By.cssSelector("a[href='" + link + "']")).click();
      List<WebElement> geoZones = app.driver.findElements(By
              .cssSelector("table#table-zones tr select[name $='[zone_code]'] option[selected ='selected']"));
      for (WebElement zone : geoZones) {
        String name = zone.getAttribute("textContent");
        originGeoZonesList.add(name);
      }
      List<String> sortedGeoZonesList = originGeoZonesList.stream().sorted().collect(Collectors.toList());
      Assert.assertTrue(sortedGeoZonesList.equals(originGeoZonesList));
      app.driver.findElement(By.name("cancel")).click();
    }
  }
}
