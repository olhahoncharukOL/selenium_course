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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 12.04.2018.
 */
public class SetDriver {
    public static WebDriver driver;
    public static JavascriptExecutor jse;
    public static WebDriverWait wait;

    @Before
    public void start() throws MalformedURLException {
        if (driver==null){
          //  FirefoxOptions options = new FirefoxOptions();
          //  options.setCapability("marionette", true);
        //    WebDriver driver = new RemoteWebDriver(new URL("http://192.168.56.102:4445/wd/hub"),options);

            driver = new ChromeDriver();
         //   driver = new FirefoxDriver();
           // driver = new InternetExplorerDriver();

          /*  FirefoxOptions options = new FirefoxOptions();
             options.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Firefox Nightly\\firefox.exe")));
            options.setCapability("marionette", true);
             driver = new FirefoxDriver(options); */
        }

        //jse = (JavascriptExecutor) driver;
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30);
    }
    @After
    public void stop() {
        if (driver!=null) {
            driver.quit();
            driver= null;
       }
    }
}
