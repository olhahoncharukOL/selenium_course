package com.olga;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

/**
 * Created by User on 05.05.2018.
 */
public class LinksInNewWindowTest extends TestBasis {
    private String newWindow;
    @Test
    public void openLinksInNewWindow(){
    LogginTest test = new LogginTest();
    test.loggin();
    driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    driver.findElement(By.cssSelector("#content tr.row a")).click();

        List<WebElement> externalLinks = driver.findElements(By.cssSelector("i.fa.fa-external-link"));

        for (int i=0; i<externalLinks.size(); i++) {
            String mainWindow = driver.getWindowHandle();
            externalLinks.get(i).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> allWindows = driver.getWindowHandles();

            for (String w: allWindows) {
                if (!w.equals(mainWindow)) {
                  newWindow = w;
                }
            }
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
            externalLinks = driver.findElements(By.cssSelector("i.fa.fa-external-link"));
        }
    }
}
