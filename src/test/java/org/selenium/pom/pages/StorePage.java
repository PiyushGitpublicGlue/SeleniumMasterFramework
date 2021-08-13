package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.ProductSearch;
import org.selenium.pom.pages.components.ProductThumbnail;
import org.selenium.pom.pages.components.SideBarThumbnail;

public class StorePage extends BasePage {

    private By searchBox = By.id("woocommerce-product-search-field-0");
    private By searchBnt = By.cssSelector("button[value='Search']");
    private By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    private By addToCartBtn = By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']");
    private ProductThumbnail productThumbnail;
    private ProductSearch productSearch;
    private SideBarThumbnail sideBarThumbnail;


    public SideBarThumbnail getSideBarThumbnail() {
        return sideBarThumbnail;
    }

    public void setSideBarThumbnail(SideBarThumbnail sideBarThumbnail) {
        this.sideBarThumbnail = sideBarThumbnail;
    }





    public StorePage search(String Text){
        productSearch.search(Text);
        return this;
    }



    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }



    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail=new ProductThumbnail(driver);
        productSearch=new ProductSearch(driver);
        sideBarThumbnail=new SideBarThumbnail(driver);
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


    public String getTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }


}
