package com.olga;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by User on 25.04.2018.
 */
public class TestBasis extends SetDriver{

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

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return  false;
        }
    }

    public boolean isElementsPresent(By locator) {
            List<WebElement> list= driver.findElements(locator);
            if (list.size()==0) {
                return false;
            }
            return  true;

    }
}
