package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class WomenPage extends BasePage {


    private By title = By.cssSelector(".woocommerce-products-header__title.page-title");

    public WomenPage(WebDriver driver) {
        super(driver);
    }


    public boolean isLoaded(){

        return wait.until(ExpectedConditions.urlContains("/product-category/women"));
    }


    public WomenPage load(){

        load("/product-category/women");
        return this;
    }



    public String getTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }


}
