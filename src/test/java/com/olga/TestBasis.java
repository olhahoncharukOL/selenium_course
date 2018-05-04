package com.olga;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;

/**
 * Created by User on 25.04.2018.
 */
public class TestBasis extends SetDriver{
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final String IN = "0123456789";
    static SecureRandom rnd = new SecureRandom();
    static List<WebElement> menuListOfItems;
    static List<WebElement> menuItemSubitems;

    public boolean isCollectionTextSorted(List<WebElement> list) {
        List<String> listToString = new ArrayList<String>();
        for (WebElement element: list) {
            listToString.add(element.getText());
        }
        Collections.sort(listToString);
        if (!(list.size()==listToString.size())) {
            return false;
        }
        for (int i=0; i< list.size(); i++) {
            if (!(list.get(i).getText().equals(listToString.get(i)))) {
                return false;
            }
        }
        return true;
    }

    public boolean isElementPresent( By locator) {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
        finally {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
    }

    public boolean isElementPresent( WebElement element, By locator) {

        try {
            element.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return  false;
        }
    }

    public boolean areElementsPresent(By locator) {
            List<WebElement> list= driver.findElements(locator);
            if (list.size()==0) {
                return false;
            }
            return  true;
    }


    public boolean areElementsPresent(WebElement element, By locator) {
        List<WebElement> list= element.findElements(locator);
        if (list.size()==0) {
            return false;
        }
        return  true;
    }

    public String randomEmailGenerator( int len, String domain ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString() + "@" +domain;
    }

    public String randomStringGenerator( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public String randomNumberGenerator( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( IN.charAt( rnd.nextInt(IN.length()) ) );
        return sb.toString();
    }

    public void logout(List<WebElement> links, String logout) {

        for (WebElement link: links) {
            if ( link.getAttribute("textContent").equals(logout)) {
                link.click();
            }
        }
    }

    public List<WebElement> findMenuListOfItems() {
        menuListOfItems = driver.findElements(By.cssSelector("#app-"));
        return menuListOfItems;
    }

    public List<WebElement> findMenuItemSubitems() {
        menuItemSubitems = driver.findElements(By.cssSelector("#box-apps-menu li li"));
        return menuItemSubitems;
    }

    public void setDatepicker(WebDriver driver, String cssSelector, String date) {
        new WebDriverWait(driver, 30000).until(
                (WebDriver d) -> d.findElement(By.cssSelector(cssSelector)).isDisplayed());
        JavascriptExecutor.class.cast(driver).executeScript(
                String.format("$('%s').datepicker('setDate', '%s')", cssSelector, date));
    }
}
