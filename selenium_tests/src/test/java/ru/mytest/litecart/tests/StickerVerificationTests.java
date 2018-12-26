package ru.mytest.litecart.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StickerVerificationTests extends TestBase {

  @Test
  public void testStickerVerification() {
    List<WebElement> ducks = app.driver.findElements(By.cssSelector("div#main a.link"));
    for (WebElement duck : ducks) {
      System.out.println("1. Товар: " + duck.getAttribute("href"));
      List<WebElement> stickers = duck.findElements(By.cssSelector("div.image-wrapper > div.sticker"));
      if (stickers.size() == 0) {
        System.out.println("Товар " + duck.getAttribute("href") + " не имеет стикера");
      } if (stickers.size() == 1) {
        System.out.println("Товар " + duck.getAttribute("href") + " имеет один стикер " + stickers.iterator().next().getText());
      } else {
        System.out.println("Товар " + duck.getAttribute("href") + " имеет более одного стикера");
      }
    }
  }
}
