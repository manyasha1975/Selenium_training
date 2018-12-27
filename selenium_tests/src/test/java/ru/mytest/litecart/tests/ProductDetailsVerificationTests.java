package ru.mytest.litecart.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductDetailsVerificationTests extends TestBase {

  @Test
  public void testProductDetailsVerification() {
    List<WebElement> ducks = app.driver.findElements(By.cssSelector("div#box-campaigns ul.listing-wrapper.products li"));
    //get information from main page
    String name = ducks.get(0).findElement(By.cssSelector("div.name")).getAttribute("textContent");
    String manufacturer = ducks.get(0).findElement(By.cssSelector("div.manufacturer")).getAttribute("textContent");
    //tags s and strong in locators - to verify strikethrough and bold text
    String regularPrice = ducks.get(0).findElement(By.cssSelector("div.price-wrapper > s.regular-price")).getAttribute("textContent");
    String regularPriceColor = ducks.get(0).findElement(By.cssSelector("div.price-wrapper > s.regular-price")).getCssValue("color");
    Dimension regularPriceSize = ducks.get(0).findElement(By.cssSelector("div.price-wrapper > s.regular-price")).getSize();
    String campaignPrice = ducks.get(0).findElement(By.cssSelector("div.price-wrapper > strong.campaign-price")).getAttribute("textContent");
    String campaignPriceColor = ducks.get(0).findElement(By.cssSelector("div.price-wrapper > strong.campaign-price")).getCssValue("color");
    Dimension campaignPriceSize = ducks.get(0).findElement(By.cssSelector("div.price-wrapper > strong.campaign-price")).getSize();
    System.out.println("From 1-st page: " + regularPriceColor + ", " + campaignPriceColor + ", " + regularPriceSize + ", " + campaignPriceSize);

    //get information from subcategory page
    ducks.get(0).findElement(By.cssSelector("a.link")).click();
    String subName = app.driver.findElement(By.tagName("h1")).getAttribute("textContent");
    String subManufacturer = app.driver.findElement(By.cssSelector("div#box-product div.manufacturer img")).getAttribute("title");
    //tags s and strong in locators - to verify strikethrough and bold text
    String subRegularPrice = app.driver.findElement(By.cssSelector("div#box-product div.price-wrapper > s.regular-price")).getAttribute("textContent");
    String subRegularPriceColor = app.driver.findElement(By.cssSelector("div#box-product div.price-wrapper > s.regular-price")).getCssValue("color");
    Dimension subRegularPriceSize = app.driver.findElement(By.cssSelector("div#box-product div.price-wrapper > s.regular-price")).getSize();
    String subCampaignPrice = app.driver.findElement(By.cssSelector("div#box-product div.price-wrapper > strong.campaign-price")).getAttribute("textContent");
    String subCampaignPriceColor = app.driver.findElement(By.cssSelector("div#box-product div.price-wrapper > strong.campaign-price")).getCssValue("color");
    Dimension subCampaignPriceSize = app.driver.findElement(By.cssSelector("div#box-product div.price-wrapper > strong.campaign-price")).getSize();
    System.out.println("From 2-nd page: " + subRegularPriceColor + ", " + subCampaignPriceColor + ", " + subRegularPriceSize + ", " + subCampaignPriceSize);

    //verify names
    Assert.assertTrue(name.equals(subName));
    Assert.assertTrue(manufacturer.equals(subManufacturer));

    //verify price
    Assert.assertTrue(regularPrice.equals(subRegularPrice));
    Assert.assertTrue(campaignPrice.equals(subCampaignPrice));

    //verify colors
    int[] regularPriceColorByNumber = convertToInt(regularPriceColor);
    assert (regularPriceColorByNumber[0] == regularPriceColorByNumber[1]
            & regularPriceColorByNumber[0] == regularPriceColorByNumber[2]);

    int[] subRegularPriceColorByNumber = convertToInt(subRegularPriceColor);
    assert (subRegularPriceColorByNumber[0] == subRegularPriceColorByNumber[1]
            & subRegularPriceColorByNumber[0] == subRegularPriceColorByNumber[2]);

    int[] campaignPriceColorByNumber = convertToInt(campaignPriceColor);
    assert (campaignPriceColorByNumber[0] == 204
            & campaignPriceColorByNumber[1] == 0 & campaignPriceColorByNumber[2] == 0);

    int[] subCampaignPriceColorByNumber = convertToInt(subCampaignPriceColor);
    assert (subCampaignPriceColorByNumber[0] == 204
            & subCampaignPriceColorByNumber[1] == 0 & subCampaignPriceColorByNumber[2] == 0);

    //verify size
    assert (regularPriceSize.getWidth() < campaignPriceSize.getWidth()
            & regularPriceSize.getHeight() < campaignPriceSize.getHeight());

    assert (subRegularPriceSize.getWidth() < subCampaignPriceSize.getWidth()
            & subRegularPriceSize.getHeight() < subCampaignPriceSize.getHeight());
  }

  private int[] convertToInt(String stringColor) {
    String cleanedColor = stringColor.replaceAll("\\s","").replaceAll("[rgba()]","");
    return Arrays.stream(cleanedColor.split(",")).mapToInt(Integer::parseInt).toArray();
  }
}
