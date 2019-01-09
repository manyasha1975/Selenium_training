package ru.mytest.litecart.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class AdditionToBasketTests extends TestBase {

  @Test
  public void testAdditionToBasket() {
    //addition to cart
    for (int i = 0; i < 3; i++) {
      String quantity = app.product().getQuantity();
      String newQuantity = Integer.toString(Integer.parseInt(quantity) + 1);
      app.product().selectFirstProduct();
      app.product().addToCart();
      app.product().waitQuantity(newQuantity);
      app.goTo().homePage();
    }
    //deletion from cart
    app.goTo().cartPage();
    app.product().removeAllFromCart();
    String noProducts = app.product().cartIsEmpty();
    Assert.assertTrue(noProducts.equals("There are no items in your cart."));
    app.goTo().returnFromCart();
  }



}
