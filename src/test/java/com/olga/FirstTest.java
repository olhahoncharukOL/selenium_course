package com.olga;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.security.SecureRandom;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by User on 12.04.2018.
 */
public class FirstTest extends SetDriver{
    @Test
    public void firstTest()  {
        driver.navigate().to("http://google.com");
        WebElement element = driver.findElement(By.name("q"));
        // Enter something to search for
        element.sendKeys("my first search");
        WebElement elementSec =  driver.findElement(By.name("btnK"));
        elementSec.submit();
        wait.until(titleIs("my first search - Поиск в Google"));
    }
}
