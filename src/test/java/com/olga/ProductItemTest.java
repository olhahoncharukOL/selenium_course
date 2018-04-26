package com.olga;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by User on 25.04.2018.
 */
public class ProductItemTest extends TestBasis{
    @Test
    public void checkProductItem(){
    // MAIN Page
       driver.get("http://localhost/litecart/en/");
       WebElement mainPage = driver.findElement(By.cssSelector("#box-campaigns ul li"));

        // Regular price attributes on the main page
        //color
        String colorRegMain = mainPage.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        String[] colorRegArr = colorRegMain.substring(5, colorRegMain.length()-1).split(", ");
        if (!(colorRegArr[0].equals(colorRegArr[1]) && colorRegArr[1].equals(colorRegArr[2]))) {
            System.out.println("product regular price's color is not grey on the main page");
            Assert.fail();
        }
        System.out.println("product regular price's color is grey on the main page");

        //is font is line-through
        if (!mainPage.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line").equals("line-through")) {
            System.out.println("product regular price's is not line-through on the main page");
            Assert.fail();
        }
        System.out.println("product regular price's is line-through on the main page");

        String regPriceFontMain = mainPage.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
        Double regrPriceFontSizeMain = Double.parseDouble(regPriceFontMain.substring(0,regPriceFontMain.length()-2));

        // Campaign price attributes on the main page
        //color
        String colorCampMain = mainPage.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        String[] colorCampMainArr = colorCampMain.substring(5,colorCampMain.length()-1).split(", ");
        if(!(colorCampMainArr[1].equals("0")&&colorCampMainArr[2].equals("0"))) {
            System.out.println("campaign price'color is not red on the main page");
            Assert.fail();
        }
        System.out.println("campaign price'color is red on the main page");

        //campaign price fond is bold
        if (!mainPage.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight").equals("700")) {
            System.out.println("product campaign price's font is not bold on the main page");
            Assert.fail();
        }
        System.out.println("product campaign price's font is bold on the main page");

        // regular and campaign price fond SIZE comparison on the main
        String priceFontCamMain = mainPage.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");
        Double campPriceFontSizeMain = Double.parseDouble(priceFontCamMain.substring(0,priceFontCamMain.length()-2));

        if (regrPriceFontSizeMain>=campPriceFontSizeMain) {
            System.out.println("on the main page: product campaign price's FONT is less or equal to the FONT of the regular price");
            Assert.fail();
        }
        System.out.println("on the main page: product campaign price's FONT is bigger than FONT of the regular price");

       String productNameMain = mainPage.findElement(By.cssSelector("div.name")).getText();
       String regularPriceMain = mainPage.findElement(By.cssSelector("s.regular-price")).getText();
       String campaignPriceMain= mainPage.findElement(By.cssSelector("strong.campaign-price")).getText();

    // PRODUCT's Page

       driver.findElement(By.cssSelector("#box-campaigns ul li")).click();
      //  jse.executeScript("window.open(\"http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1\")");

        driver.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");
        WebElement productPage = driver.findElement(By.cssSelector("#box-product"));

        String productNamePage =  productPage.findElement(By.cssSelector("h1.title")).getText();
        String regularPricePage = productPage.findElement(By.cssSelector("s.regular-price")).getText();
        String campaignPricePage = productPage.findElement(By.cssSelector("strong.campaign-price")).getText();

        //is product name is the same on the main and product's page
        if (!productNameMain.equals(productNamePage)) {
            System.out.println("product's name is not the same on the main and product's page");
            Assert.fail();
        }
        System.out.println("product's name is the same on the main and product's page");

        //is product regular price is the same on the main and product's page
        if (!regularPriceMain.equals(regularPricePage)) {
            System.out.println("product's regular price is not the same on the main and product's page");
            Assert.fail();
        }
        System.out.println("product's regular price is the same on the main and product's page");

        //is product campaign price is the same on the main and product's page
        if (!campaignPriceMain.equals(campaignPricePage)) {
            System.out.println("product's campaign price not the same on the main and product's page");
            Assert.fail();
        }
        System.out.println("product's campaign price is the same on the main and product's page");

        // Regular price attributes on the product's page
        // color
         String colorReg = productPage.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
         String[] colorRegArray = colorReg.substring(5,colorReg.length()-1).split(", ");

        if (!(colorRegArray[0].equals(colorRegArray[1]) && colorRegArray[1].equals(colorRegArray[2]))) {
            System.out.println("product regular price's color is not grey on the product's page");
            Assert.fail();
        }
         System.out.println("product regular price's color is grey on the product's page");
        // is regular price line-through
            if (!productPage.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line").equals("line-through")) {
            System.out.println("product regular price's is not line-through on the product's page");
            Assert.fail();
        }
        System.out.println("product regular price's is line-through on the product's page");

         // regular and campaign price fond SIZE comparison on the product's page
         String fontSize = productPage.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
         Double fontSizeRegular = Double.parseDouble(fontSize.substring(0,fontSize.length()-2));

        // Campaign price attributes on the product's page
        //color
         String colorCamp = productPage.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
         String[] colorCampArr = colorCamp.substring(5,colorCamp.length()-1).split(", ");
          if(!(colorCampArr[1].equals("0")&&colorCampArr[2].equals("0"))) {
              System.out.println("Campaign price's color is not red on the product's page");
              Assert.fail();
          }
        System.out.println("Campaign price's color is red on the product's page");
         //campaign price fond is bold
          if (!productPage.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight").equals("700")) {
          System.out.println("product campaign price's font is not bold on the product page");
           Assert.fail();
          }
        System.out.println("product campaign price's font is bold on the product page");

        String fontSizeCam = productPage.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");
        Double fontSizeCampaign = Double.parseDouble(fontSizeCam.substring(0,fontSizeCam.length()-2));

        if (fontSizeRegular>=fontSizeCampaign) {
            System.out.println("on the product's page campaign price's font size is less or equal to regular price font size");
            Assert.fail();
        }
        System.out.println("on the product's page campaign price's font size is bigger than regular price font size");
    }
    }

