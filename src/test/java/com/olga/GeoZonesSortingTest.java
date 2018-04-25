package com.olga;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 25.04.2018.
 */
public class GeoZonesSortingTest extends TestBasis {
    List<WebElement> tableOfCountries;
    List<WebElement> countyZones;
    @Test
    public void checkGeoZonesSorting() {

        LogginTest test = new LogginTest();
        test.loggin();
        findTableOfCountries();
        for (int i=0; i<tableOfCountries.size(); i++) {
            WebElement link = tableOfCountries.get(i);
            link.click();
            countyZones = driver.findElements(By.cssSelector("#table-zones td select[name *= zone_code]"));

            if (isCollectionTextSorted(countyZones)==false) {
                AssertionError error = new AssertionError();
                System.out.println(error.getMessage() + "Not sorted GeoZones on the page" + driver.getCurrentUrl());
                Assert.fail();
            }
            System.out.println("Zones for " + driver.getCurrentUrl() + " is sorted");

            findTableOfCountries();
        }
    }

    public void findTableOfCountries() {
        tableOfCountries = new ArrayList<WebElement>();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
       tableOfCountries = driver.findElements(By.cssSelector(".dataTable .row a:not([title])"));
    }
}
