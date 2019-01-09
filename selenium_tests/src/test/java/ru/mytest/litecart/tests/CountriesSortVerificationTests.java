package ru.mytest.litecart.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CountriesSortVerificationTests extends TestBase {

  @Before
  public void loginAdmin() {
    app.session().loginAs("admin", "admin");
  }

  @Test
  public void testCountriesSortVerification() {
    app.driver.findElement(By.cssSelector("a[href='http://localhost/litecart/admin/?app=countries&doc=countries']")).click();
    List<WebElement> countries = app.driver.findElements(By.cssSelector("table.dataTable tr.row"));
    List<String> originCountryList = new ArrayList<String>();
    List<String> countriesWithGeoZones = new ArrayList<String>();
    for (WebElement country : countries) {
      List<WebElement> columns = country.findElements(By.cssSelector("td"));
      String link = country.findElement(By.cssSelector("a")).getAttribute("href");
      String name = columns.get(4).getAttribute("textContent");
      String count = columns.get(5).getAttribute("textContent");
      originCountryList.add(name);
      if (!count.equals("0")) {
        countriesWithGeoZones.add(link);
      }
    }
    List<String> sortedCountryList = originCountryList.stream().sorted().collect(Collectors.toList());
    Assert.assertTrue(sortedCountryList.equals(originCountryList));
    for (String link : countriesWithGeoZones) {
      List<String> originGeoZonesList = new ArrayList<String>();
      app.driver.findElement(By.cssSelector("a[href='" + link + "']")).click();
      List<WebElement> geoZones = app.driver.findElements(By.cssSelector("table#table-zones tr input[name $='[name]']"));
      for (WebElement zone : geoZones) {
        String name = zone.getAttribute("value");
        if (!name.equals("")) {
          originGeoZonesList.add(name);
        }
      }
      List<String> sortedGeoZonesList = originGeoZonesList.stream().sorted().collect(Collectors.toList());
      Assert.assertTrue(sortedGeoZonesList.equals(originGeoZonesList));
      app.driver.findElement(By.name("cancel")).click();
    }
  }
}
