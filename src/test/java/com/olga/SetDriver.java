package com.olga;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 12.04.2018.
 */
public class SetDriver {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        //driver = new InternetExplorerDriver();
        wait = new WebDriverWait(driver,15);
    }
    @After
    public void stop() {
        driver.quit();
        driver= null;
    }
}
