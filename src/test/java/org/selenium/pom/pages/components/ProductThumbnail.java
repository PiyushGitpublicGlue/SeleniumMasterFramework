package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.CartPage;

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

    private By getProductPriceByElement(String productName){
        return By.xpath("//*[contains(text(),'"+productName+"')]//..//..//span[2]//bdi");
    }

    public String getProductPrice(String productName){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(getProductPriceByElement(productName))).getText();

    }

    public CartPage clickOnViewCartBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
        return new CartPage(driver);
    }

    public double productPriceInInt(String productName){

        String priceInString = getProductPrice(productName);
        priceInString = priceInString.replaceAll("\\$","");
        return Double.parseDouble(priceInString);

    }
}
