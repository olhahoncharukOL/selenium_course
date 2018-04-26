package com.olga;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;

/**
 * Created by User on 25.04.2018.
 */
public class RightPageTest_FireFox extends TestBasis {
    @Before
    public void start() {
        if (driver == null) {
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Firefox Nightly\\firefox.exe")));
            options.setCapability("marionette", true);
            driver = new FirefoxDriver(options);
        }
    }
    @Test
    public void checkProductItem() {
        // MAIN Page
        driver.get("http://localhost/litecart/en/");
        WebElement mainPage = driver.findElement(By.cssSelector("#box-campaigns ul li"));

        // Regular price attributes on the main page
        //color
        if (isColorGrey(mainPage, By.cssSelector("s.regular-price")) == false) {
            System.out.println("product regular price's color is not grey on the main page");
            Assert.fail();
        }
        //is font is line-through
        if (!mainPage.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line").equals("line-through")) {
            System.out.println("product regular price's is not line-through on the main page");
            Assert.fail();
        }

        String regPriceFontMain = mainPage.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
        Double regrPriceFontSizeMain = Double.parseDouble(regPriceFontMain.substring(0, regPriceFontMain.length() - 2));

        // Campaign price attributes on the main page
        //color
        if (isColorRed(mainPage, By.cssSelector("strong.campaign-price"))==false) {
            System.out.println("campaign price'color is not red on the main page");
            Assert.fail();
        }
        //campaign price fond is bold
        if (!mainPage.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight").equals("900")) {
            System.out.println(mainPage.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight"));
            System.out.println("product campaign price's font is not bold on the main page");
            Assert.fail();
        }
        // regular and campaign price fond SIZE comparison on the main
        String priceFontCamMain = mainPage.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");
        Double campPriceFontSizeMain = Double.parseDouble(priceFontCamMain.substring(0, priceFontCamMain.length() - 2));

        if (regrPriceFontSizeMain >= campPriceFontSizeMain) {
            System.out.println("on the main page: product campaign price's FONT is less or equal to the FONT of the regular price");
            Assert.fail();
        }

        String productNameMain = mainPage.findElement(By.cssSelector("div.name")).getText();
        String regularPriceMain = mainPage.findElement(By.cssSelector("s.regular-price")).getText();
        String campaignPriceMain = mainPage.findElement(By.cssSelector("strong.campaign-price")).getText();

        // PRODUCT's Page

        driver.findElement(By.cssSelector("#box-campaigns ul li")).click();
        //  jse.executeScript("window.open(\"http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1\")");

        driver.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");
        WebElement productPage = driver.findElement(By.cssSelector("#box-product"));

        String productNamePage = productPage.findElement(By.cssSelector("h1.title")).getText();
        String regularPricePage = productPage.findElement(By.cssSelector("s.regular-price")).getText();
        String campaignPricePage = productPage.findElement(By.cssSelector("strong.campaign-price")).getText();

        //is product name is the same on the main and product's page
        if (!productNameMain.equals(productNamePage)) {
            System.out.println("product's name is not the same on the main and product's page");
            Assert.fail();
        }
        //is product regular price is the same on the main and product's page
        if (!regularPriceMain.equals(regularPricePage)) {
            System.out.println("product's regular price is not the same on the main and product's page");
            Assert.fail();
        }
        //is product campaign price is the same on the main and product's page
        if (!campaignPriceMain.equals(campaignPricePage)) {
            System.out.println("product's campaign price not the same on the main and product's page");
            Assert.fail();
        }

        // Regular price attributes on the product's page
        // color
        if (isColorGrey(productPage,By.cssSelector("s.regular-price"))==false) {
            System.out.println("product regular price's color is not grey on the product's page");
            Assert.fail();
        }
        // is regular price line-through
        if (!productPage.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line").equals("line-through")) {
            System.out.println("product regular price's is not line-through on the product's page");
            Assert.fail();
        }
        // regular and campaign price fond SIZE comparison on the product's page
        String fontSize = productPage.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
        Double fontSizeRegular = Double.parseDouble(fontSize.substring(0, fontSize.length() - 2));

        // Campaign price attributes on the product's page
        //color
        if (isColorRed(productPage,By.cssSelector("strong.campaign-price"))==false ) {
            System.out.println("Campaign price's color is not red on the product's page");
            Assert.fail();
        }
        //campaign price fond is bold
        if (!productPage.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight").equals("700")) {
            System.out.println("product campaign price's font is not bold on the product page");
            Assert.fail();
        }
        // regular and campaign price fond SIZE comparison on the product page
        String fontSizeCam = productPage.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");
        Double fontSizeCampaign = Double.parseDouble(fontSizeCam.substring(0, fontSizeCam.length() - 2));

        if (fontSizeRegular >= fontSizeCampaign) {
            System.out.println("on the product's page campaign price's font size is less or equal to regular price font size");
            Assert.fail();
        }
    }

    public boolean isColorGrey(WebElement element, By locator) {
        String color = element.findElement(locator).getCssValue("color");
        String[] colorArr = color.substring(4, color.length() - 1).split(", ");

        if (!(colorArr[0].equals(colorArr[1]) && colorArr[1].equals(colorArr[2]))) {
            return false;
        }
        return true;
    }

    public boolean isColorRed(WebElement element, By locator) {
        String color = element.findElement(locator).getCssValue("color");
        String[] colorArr = color.substring(4, color.length() - 1).split(", ");
        if (!(colorArr[1].equals("0") && colorArr[2].equals("0"))) {
            return false;
        }
        return true;
    }
}

