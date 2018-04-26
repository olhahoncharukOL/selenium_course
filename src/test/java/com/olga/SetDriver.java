package com.olga;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 12.04.2018.
 */
public class SetDriver {
    public static WebDriver driver;
    public static JavascriptExecutor jse;
    public static WebDriverWait wait;

    @Before
    public void start() {
        if (driver==null){
            driver = new ChromeDriver();
            jse = (JavascriptExecutor) driver;
            //driver = new InternetExplorerDriver();
           /* FirefoxOptions options = new FirefoxOptions();
             options.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Firefox Nightly\\firefox.exe")));
             driver = new FirefoxDriver(options); */
         //    driver = new InternetExplorerDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
             wait = new WebDriverWait(driver, 30);
        }

       // old version of FireFox
      /* FirefoxOptions options = new FirefoxOptions();
        options.setCapability("marionette", false);
        driver = new FirefoxDriver(options); */
    }
    @After
    public void stop() {
        driver.quit();
        driver= null;
    }
}
