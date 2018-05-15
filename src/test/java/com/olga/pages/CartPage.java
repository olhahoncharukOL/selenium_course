package com.olga.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by User on 15.05.2018.
 */
public class CartPage extends Page{
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void cleanTheCart() {
        driver.findElement(By.cssSelector("#box-checkout-cart li.shortcut a")).click();
        WebElement productsTable = driver.findElement(By.cssSelector("#order_confirmation-wrapper tbody"));
        String productsTableSize = productsTable.getAttribute("childElementCount");
        Integer productsTableSizeInt = Integer.parseInt(productsTableSize);

        List<WebElement> removeButtons = driver.findElements(By.cssSelector("#box-checkout-cart p button[name=remove_cart_item]"));

        for (int i = 0; i < removeButtons.size(); ) {
            if (removeButtons.size()==1) {
                removeButtons.get(0).click();
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#checkout-cart-wrapper p em")));
                break;
            }
            removeButtons.get(0).click();
            productsTableSizeInt = productsTableSizeInt-1;
            String productTableSizeNew = productsTableSizeInt.toString();
            wait.until(ExpectedConditions.attributeToBe(By.cssSelector("#order_confirmation-wrapper tbody"),"childElementCount",
                    productTableSizeNew));
            removeButtons = driver.findElements(By.cssSelector("#box-checkout-cart p button[name=remove_cart_item]"));
        }
    }

    }

