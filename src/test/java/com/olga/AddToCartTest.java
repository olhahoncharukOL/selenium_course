package com.olga;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

/**
 * Created by User on 02.05.2018.
 */
public class AddToCartTest extends TestBasis{
    static WebElement firstProduct;
    static  WebElement productPriceElement;
    @Test
    public void addItemToCart() {
        driver.get("http://localhost/litecart/en/");
        for (int i = 0; i < 3; i++) {
            addProductToCart();
        }
        driver.findElement(By.cssSelector("#cart a.link")).click();

        WebElement productsTable = driver.findElement(By.cssSelector("#order_confirmation-wrapper tbody"));
        String productsTableSize = productsTable.getAttribute("childElementCount");
        Integer productsTableSizeInt = Integer.parseInt(productsTableSize);

        driver.findElement(By.cssSelector("#box-checkout-cart li.shortcut a")).click();

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
        firstProduct = driver.findElement(By.cssSelector(".product"));
        firstProduct.click();
        if (isElementPresent(By.cssSelector("#box-product select[name='options[Size]']"))) {
            Select size = new Select(driver.findElement(By.cssSelector("#box-product select[name='options[Size]']")));
            size.selectByValue("Small");
        }
        driver.findElement(By.cssSelector("#box-product button[name=add_cart_product]")).click();
        waitUntilCartRefreshed();
        driver.findElement(By.cssSelector("#site-menu-wrapper a")).click();
    }
}
