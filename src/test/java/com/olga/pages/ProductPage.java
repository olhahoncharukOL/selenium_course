package com.olga.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by User on 15.05.2018.
 */
public class ProductPage extends Page{
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitUntilCartRefreshed( ) {
        WebElement cart = driver.findElement(By.cssSelector("#cart"));
//Before action
        //Quantity in the cart
        WebElement quantityElement = cart.findElement(By.cssSelector(".quantity"));
        String quantityBeforeStr = quantityElement.getAttribute("textContent");
        Integer quantityBeforeInt = Integer.parseInt(quantityBeforeStr);
        //Sum in the cart
        WebElement sumElement = cart.findElement(By.cssSelector(".formatted_value"));
        String sumBefore = sumElement.getAttribute("textContent");
        String sumBeforeStr =sumBefore.substring(1,sumBefore.length());
        Integer sumBeforeInt = Integer.parseInt(sumBeforeStr);
        //Product price
        WebElement productPriceElement;
        if (isElementPresent(By.cssSelector("#box-product span.price"))){
            productPriceElement = driver.findElement(By.cssSelector("#box-product span.price"));
        } else {
            productPriceElement = driver.findElement(By.cssSelector("#box-product strong.campaign-price"));
        }
        String productPrice = productPriceElement.getAttribute("textContent");
        Integer productPriceInt = Integer.parseInt(productPrice.substring(1,productPrice.length()));
//After action
        //Quantity
        Integer quantityAfterInt= quantityBeforeInt+ new Integer(1);
        String quantityAfterStr = quantityAfterInt.toString(); // condition for check
        //Sum
        Integer sumAfterInt = sumBeforeInt + productPriceInt;
        String sumAfterStr = "$" +sumAfterInt;

//Wait until quantity and sum in the cart are refreshed
        wait.until(
                ExpectedConditions.and(
                        ExpectedConditions.attributeToBe(quantityElement,"textContent", quantityAfterStr),
                        ExpectedConditions.and(ExpectedConditions.attributeToBe(sumElement,"textContent", sumAfterStr))
                )
        );
    }

    public void addProductToCart() {
        if (isElementPresent(By.cssSelector("#box-product select[name='options[Size]']"))) {
            Select size = new Select(driver.findElement(By.cssSelector("#box-product select[name='options[Size]']")));
            size.selectByValue("Small");
        }
        driver.findElement(By.cssSelector("#box-product button[name=add_cart_product]")).click();
        waitUntilCartRefreshed();
        driver.findElement(By.cssSelector("#site-menu-wrapper a")).click();
    }

}
