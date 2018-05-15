package com.olga.app;

import com.olga.pages.CartPage;
import com.olga.pages.MainPage;
import com.olga.pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by User on 15.05.2018.
 */
public class Application {
    private WebDriver driver;

    private MainPage mainPage;
    private ProductPage productPage;
    private CartPage cartPage;

    public Application() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void add_K_productsToCart(Integer k) {

        mainPage.open();
        for (int i = 0; i < k; i++) {
            mainPage.chooseFirstProduct();
            productPage.addProductToCart();
        }
    }

    public void cleanTheCart() {
        mainPage.checkOut();
        cartPage.cleanTheCart();
    }
}
