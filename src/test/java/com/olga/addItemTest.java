package com.olga;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.util.*;

/**
 * Created by User on 30.04.2018.
 */
public class addItemTest extends TestBasis{
    static String productName;
    @Test
    public void addItem()  {
        LogginTest test= new LogginTest();
        test.loggin();

        findMenuListOfItems();
        for (WebElement element: menuListOfItems) {
           if(element.getText().equals("Catalog")) {
               element.click();
               break;
           }
        }
        if (!driver.getCurrentUrl().equals("http://localhost/litecart/admin/?app=catalog&doc=catalog")) {
            AssertionError assertError = new AssertionError();
            System.out.println(assertError.getMessage());
            Assert.fail();
        }
        List<WebElement> links = driver.findElements(By.cssSelector("#content a[class=button]"));
        for (WebElement element: links) {
            if (element.getText().equals("Add New Product")) {
                element.click();
                break;
            }
        }
        if (!driver.getCurrentUrl().equals("http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product")) {
            AssertionError assertError = new AssertionError();
            System.out.println(assertError.getMessage());
            Assert.fail();
        }
    //General
        WebElement tabGeneral = driver.findElement(By.cssSelector("#tab-general"));

        List<WebElement> status = driver.findElements(By.cssSelector("input[name=status]"));

      if (status.get(0).isSelected()) {
          status.get(1).click();
      } else {
          status.get(0).click();
      }
      productName= "Test Duck" +randomNumberGenerator(3);
      tabGeneral.findElement(By.cssSelector("input[name^=name]")).sendKeys(productName);
      tabGeneral.findElement(By.cssSelector("input[name=code]")).sendKeys(randomNumberGenerator(5));

      List<WebElement> categories = tabGeneral.findElements(By.cssSelector("input[name^=categories]"));
      if (categories.get(0).isSelected()) {
          categories.get(0).click();
      }
          categories.get(1).click();

      WebElement defaultCategory = tabGeneral.findElement(By.cssSelector("select[name=default_category_id]"));
      if (!defaultCategory.getText().equals("Rubber Ducks"))  {
          Assert.fail();
      }
         List<WebElement> gender = tabGeneral.findElements(By.cssSelector("input[name^=product_groups]"));
         if (!gender.get(2).isSelected()) {
             gender.get(2).click();
         }
        WebElement quantity = tabGeneral.findElement(By.cssSelector("input[name=quantity]"));
        quantity.clear();
        quantity.sendKeys(randomNumberGenerator(1));

        Select quantityUnit= new Select(tabGeneral.findElement(By.cssSelector("select[name=quantity_unit_id]")));
        if (!quantityUnit.getFirstSelectedOption().getText().equals("pcs")) {
            quantityUnit.selectByValue("1");
        }

        Select deliveryStatus= new Select(tabGeneral.findElement(By.cssSelector("select[name=delivery_status_id]")));
        if (!deliveryStatus.getFirstSelectedOption().getText().equals("3-5 days")) {
        deliveryStatus.selectByValue("1");
        }

        Select soldOutStatus = new Select(tabGeneral.findElement(By.cssSelector("select[name=sold_out_status_id]")));
       if (!soldOutStatus.getFirstSelectedOption().getText().equals("Temporary sold out")){
           soldOutStatus.selectByValue("2");
       }
        WebElement uploadImage = tabGeneral.findElement(By.cssSelector("input[name^=new_images]"));
        uploadImage.sendKeys(Paths.get(System.getProperty("user.dir")) + "\\duck.jpg");

        WebElement validFrom = tabGeneral.findElement(By.cssSelector("input[name='date_valid_from'][type='date']"));
        validFrom.sendKeys("01.01.2018");

        WebElement validTo = tabGeneral.findElement(By.cssSelector("input[name='date_valid_to'][type='date']"));
        validTo.sendKeys("30.03.2020");

    //Information
        driver.findElement(By.cssSelector("a[href='#tab-information']")).click();
        wait = new WebDriverWait(driver, 15);

        WebElement tabInformation = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#tab-information")));

        Select manufacturerId = new Select(tabInformation.findElement(By.cssSelector("select[name=manufacturer_id]")));
        manufacturerId.selectByValue("1");

        tabInformation.findElement(By.cssSelector("input[name=keywords]")).sendKeys("duck, rubber duck, cool duck");
        tabInformation.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys("it's rubber duck for children with beautiful glasses ");
        tabInformation.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys(randomStringGenerator(100));
        tabInformation.findElement(By.cssSelector("input[name^=head_title")).sendKeys("yellow duck");
        tabInformation.findElement(By.cssSelector("input[name^=meta_description]")).sendKeys("meta duck");

    //Prices
        driver.findElement(By.cssSelector("a[href='#tab-prices']")).click();
        wait = new WebDriverWait(driver, 15);
       WebElement tabPrices =  wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#tab-prices")));

       tabPrices.findElement(By.cssSelector("input[name=purchase_price]")).clear();
       tabPrices.findElement(By.cssSelector("input[name=purchase_price]")).sendKeys(randomNumberGenerator(2));
       Select purchasePriceCurrencyCode = new Select(tabPrices.findElement(By.cssSelector("select[name=purchase_price_currency_code]")));
       purchasePriceCurrencyCode.selectByValue("USD");

        tabPrices.findElement(By.cssSelector("input[name='prices[USD]']")).clear();
        tabPrices.findElement(By.cssSelector("input[name='prices[USD]']")).sendKeys(randomNumberGenerator(2));

        driver.findElement(By.cssSelector("button[name=save]")).click();

        //check if item presents
        if (!driver.getTitle().equals("Catalog | My Store")) {
            System.out.println("Title of the page is "+driver.getTitle());
            Assert.fail();
        }

       List<WebElement> table = driver.findElements(By.cssSelector(".dataTable tr a"));

        if (!isElementInDB(table)) {
            System.out.println("Our test element is not saved");
            Assert.fail();
        }
    }

    protected boolean isElementInDB(List<WebElement> table){
            for (WebElement element: table) {
                if (element.getText().equals(productName)) {
                    return true;
                }
            }
            return false;
    }
}
