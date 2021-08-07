package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.ProductThumbnail;

public class AccessoriesPage extends BasePage {


    private By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    private ProductThumbnail productThumbnail;

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }



    public AccessoriesPage(WebDriver driver) {

        super(driver);
        productThumbnail = new ProductThumbnail(driver);
    }


    public boolean isLoaded(){

        return wait.until(ExpectedConditions.urlContains("/product-category/accessories"));
    }


    public AccessoriesPage load(){

        load("/product-category/accessories");
        return this;
    }



    public String getTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }


}
