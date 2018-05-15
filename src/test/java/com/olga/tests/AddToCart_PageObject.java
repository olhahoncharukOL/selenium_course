package com.olga.tests;

import org.junit.Test;

/**
 * Created by User on 15.05.2018.
 *
 */
public class AddToCart_PageObject extends TestBasePO{
    @Test
    public void addToCart() {
        app.add_K_productsToCart(3);
        app.cleanTheCart();
    }
}
