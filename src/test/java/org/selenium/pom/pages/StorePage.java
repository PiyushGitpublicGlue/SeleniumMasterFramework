package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.ProductThumbnail;

public class StorePage extends BasePage {

    private By searchBox = By.id("woocommerce-product-search-field-0");
    private By searchBnt = By.cssSelector("button[value='Search']");
    private By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    private By addToCartBtn = By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']");
    private ProductThumbnail productThumbnail;

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }



    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail=new ProductThumbnail(driver);
    }

    public StorePage search(String Text){
        enterInSearchBox(Text).clickInSearchBtn();
        return this;
    }

    public boolean isLoaded(){

        return wait.until(ExpectedConditions.urlContains("/store"));
    }

    public boolean isSearchLoaded(){

        return wait.until(ExpectedConditions.urlContains("post_type=product"));
    }

    public StorePage load(){

        load("/store");
        return this;
    }


    public StorePage enterInSearchBox(String Text){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys(Text);
        return this;
    }

    public StorePage clickInSearchBtn(){

        wait.until(ExpectedConditions.elementToBeClickable(searchBnt)).click();
        return this;
    }

    public String getTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }


}
