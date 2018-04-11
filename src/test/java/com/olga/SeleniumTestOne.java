package com.olga;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by User on 10.04.2018.
 */
public class SeleniumTestOne {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\TestTools\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }
    @Test
    public void myFirstSeleniumTest() {
    driver.get("http://www.google.com/");
        WebElement element = driver.findElement(By.name("q"));
        // Enter something to search for
        element.sendKeys("my first search");
        WebElement elementSec =  driver.findElement(By.name("btnK"));
        elementSec.submit();
    wait.until(titleIs("my first search - Поиск в Google"));

    }
    @After
    public void stop() {
        driver.quit();
        driver= null;
    }
}
