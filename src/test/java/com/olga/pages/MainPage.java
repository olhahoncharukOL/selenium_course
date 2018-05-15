package com.olga.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by User on 15.05.2018.
 */
public class MainPage extends Page{
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("http://localhost/litecart/en/");
    }

    public void chooseFirstProduct() {
        driver.findElement(By.cssSelector(".product")).click();
    }

    public void checkOut() {
        driver.findElement(By.cssSelector("#cart a.link")).click();
    }
}
