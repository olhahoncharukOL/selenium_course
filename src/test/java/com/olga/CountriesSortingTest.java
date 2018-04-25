package com.olga;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 24.04.2018.
 */
public class CountriesSortingTest extends TestBasis {
    List<WebElement> countries;
    List<WebElement> countriesZonesQuantity;
    List<WebElement> countriesZones;

    @Test
    public void checkCountriesSorting() {

        LogginTest test = new LogginTest();
        test.loggin();
        findCountiesAndCountryZonesQuantity();

        if (isCollectionTextSorted(countries) == false) {
            AssertionError error = new AssertionError();
            System.out.println("Countries are not sorted" + error.getMessage());
            Assert.fail();

        } else {
            System.out.println("Countries are sorted correctly");
        }
        for (int i = 0; i < countriesZonesQuantity.size(); i++) {
            countriesZones = new ArrayList<WebElement>();

            if (!(countriesZonesQuantity.get(i).getText().equals("0"))) {
                countries.get(i).findElement(By.cssSelector("a")).click();
                List<WebElement> list = driver.findElements(By.cssSelector("#table-zones tr td"));

                for (WebElement element : list) {
                    if (element.getAttribute("cellIndex").equals("2")
                            && (element.findElement(By.tagName("input")).getAttribute("type").equals("hidden"))) {
                        countriesZones.add(element);
                    }
                }

                if (isCollectionTextSorted(countriesZones) == false || countriesZones.size() == 0) {
                    System.out.println(driver.getCurrentUrl() + " Country Zone are not sorted or not present");
                    Assert.fail();
                }
                System.out.println("Country's zones " + driver.getCurrentUrl() + " is sorted correctly");

                findCountiesAndCountryZonesQuantity();
            }
        }
    }

    public void findCountiesAndCountryZonesQuantity() {

        countries = new ArrayList<WebElement>();
        countriesZonesQuantity = new ArrayList<WebElement>();

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<WebElement> countriesAllMenu = driver.findElements(By.cssSelector(".row td"));

        for (int k = 0; k < countriesAllMenu.size(); k++) {

            if (countriesAllMenu.get(k).getAttribute("cellIndex").equals("4")) {
                countries.add(countriesAllMenu.get(k));
            }
            if (countriesAllMenu.get(k).getAttribute("cellIndex").equals("5")) {
                countriesZonesQuantity.add(countriesAllMenu.get(k));
            }
        }
    }
}

