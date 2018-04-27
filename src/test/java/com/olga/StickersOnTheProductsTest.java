package com.olga;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by User on 24.04.2018.
 */

public class StickersOnTheProductsTest  extends SetDriver{
    List<WebElement> productsItems;
    WebElement productItem;
    @Test
     public void  stickersOnTheProducts() {
        driver.get("http://localhost/litecart/");
        productsItems = driver.findElements(By.cssSelector(".product"));
        System.out.println(productsItems.size());
        for (WebElement element: productsItems)
        { if (isOneStickerPresent(element) == false) {
            System.out.println("Sticker are not presents on the all products items");
            Assert.fail();
        }
        }
        System.out.println("Sticker presents on the all " + productsItems.size() + " product items");
    }

public boolean isOneStickerPresent(WebElement element) {
        List<WebElement> stickers = element.findElements(By.cssSelector(".sticker"));
        if (stickers.size()==1) {
            return true;
        } else {
            return false;
        }
}
}
