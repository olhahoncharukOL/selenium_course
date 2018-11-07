package com.olga;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by User on 18.04.2018.
 */
public class MenuTest extends TestBasis {
    WebElement menuItem;
    WebElement menuSubItem;

    @Test
    public void testMenu() {
        LogginTest test = new LogginTest();
        test.loggin();

        findMenuListOfItems();
        System.out.println(menuListOfItems.get(1).getAttribute("textContent"));
        for (int i = 0; i < menuListOfItems.size(); i++) {
            findMenuListOfItems();
            menuItem = menuListOfItems.get(i);
            menuItem.click();

            AssertionError error = new AssertionError();
            if (isElementPresent(By.tagName("h1"))==false) {
                System.out.println("There is no title (h1) on the page " + driver.getCurrentUrl() + ". " + error.getMessage());
                Assert.fail();
            }
            System.out.println("Title (h1) of the page " + driver.getCurrentUrl() + " is: "
                    + driver.findElement(By.tagName("h1")).getText());

            findMenuListOfItems();

            if ((driver.findElements(By.cssSelector("ul.docs li")).size() > 0)) {
                findMenuItemSubitems();
                for (int k = 1; k < menuItemSubitems.size(); k++) {
                    findMenuItemSubitems();
                    menuSubItem = menuItemSubitems.get(k);
                    menuSubItem.click();

                    if (isElementPresent(By.tagName("h1"))==false) {
                        System.out.println("There is no title on the page " + driver.getCurrentUrl() + ". "
                                + error.getMessage());
                        Assert.fail();
                    }

                    System.out.println("Title (h1) of the page " + driver.getCurrentUrl() + " is: "
                            + driver.findElement(By.tagName("h1")).getText());
                }
            }
        }
    }
}



