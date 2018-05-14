package com.olga;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;

/**
 * Created by User on 13.05.2018.
 */
public class BrowsersLog extends TestBasis{
    @Test
    public void CheckLogs() {
        LogginTest test = new LogginTest();
        test.loggin();
      driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
      List<WebElement> items = driver.findElements(By.cssSelector(".dataTable a:not([title])[href*=product_id]"));

      System.out.println(items.size());
        for (int i=0; i< items.size(); i++) {
            items = driver.findElements(By.cssSelector(".dataTable a:not([title])[href*=product_id]"));
            items.get(i).click();
            System.out.println("Check the product: " + driver.getTitle());
            List<LogEntry> log = driver.manage().logs().get("browser").getAll();
            if (log.size() > 0) {
                AssertionError assertError = new AssertionError("Failed: Found message in Browser Console \n");
                System.out.println(assertError.getMessage() + "" + log);
                Assert.fail();
            }
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        }
      }
    }


