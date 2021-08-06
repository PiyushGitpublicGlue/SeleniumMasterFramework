package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;

public class ProductThumbnail extends BasePage {

    private By viewCartLink = By.cssSelector("a[title='View cart']");

    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }

    private By getAddToCardByElement(String productName){

        return By.cssSelector("a[aria-label='Add “"+productName+"” to your cart']");

    }

    public ProductThumbnail clickAddToCartBtn(String productName){
        By addToCartBtn = getAddToCardByElement(productName);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        return this;
    }

    public CartPage clickOnViewCartBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
        return new CartPage(driver);
    }
}
