package com.olga;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 24.04.2018.
 */
public class StickersOnTheProductsTest  extends SetDriver{
    List<WebElement> productsItems;
    WebElement productItem;
    @Test
     public void  stickersOnTheProducts() {
        driver.get("http://localhost/litecart/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        productsItems = driver.findElements(By.cssSelector("ul.listing-wrapper.products li"));

        for (WebElement element: productsItems)
        { if (isOneStickerPresent(element) == false)
            Assert.fail();
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
