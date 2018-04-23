package com.olga;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 18.04.2018.
 */
public class MenuTest extends SetDriver{
    List<WebElement> menuListOfItems;
    WebElement menuItem;
    List<WebElement> menuItemSubitems;
    WebElement menuSubItem;

    @Test
    public void testMenu() {
        LogginTest test = new LogginTest();
        test.loggin();
        findMenuListOfItems();
      for (int i=0; i< menuListOfItems.size(); i++) {
          menuItem= menuListOfItems.get(i);
          menuItem.click();

          AssertionError error = new AssertionError();
          if(driver.getTitle().equals("")) {
              System.out.println("There is no title on the page " + driver.getCurrentUrl()+". "+error.getMessage());
              Assert.fail();
          }
          System.out.println("Title of the page "+ driver.getCurrentUrl()+ "is: "+driver.getTitle());

          findMenuListOfItems();

          if ((driver.findElements(By.cssSelector("ul.docs li")).size() > 0)) {
              findMenuItemSubitems();
              for(int k=1; k<menuItemSubitems.size(); k++) {
                  menuSubItem=menuItemSubitems.get(k);
                  menuSubItem.click();
                  if(driver.getTitle().equals("")) {
                      System.out.println("There is no title on the page " + driver.getCurrentUrl()+". "+error.getMessage());
                      Assert.fail();
                  }

                  System.out.println("Title of the page "+ driver.getCurrentUrl()+ "is: "+driver.getTitle());

                  findMenuItemSubitems();
              }
          }
          findMenuListOfItems();
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
    }
