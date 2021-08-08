package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.ShippingAddress;

public class CartPage extends BasePage {

    /*private By productName = By.cssSelector("td[class='product-name'] a");
    private By checkOutBtn = By.cssSelector(".checkout-button");*/
    private By cartHeading = By.cssSelector(".has-text-align-center");

    @FindBy(css = "td[class='product-name'] a") private WebElement productName;
    @FindBy(css = ".checkout-button") @CacheLookup private WebElement checkOutBtn;
    @FindBy(id = "calc_shipping_country_field") @CacheLookup private WebElement getCountryDrp;
    @FindBy(id = "calc_shipping_state_field")@CacheLookup private WebElement getStateDrp;
    @FindBy(id = "calc_shipping_city")@CacheLookup private WebElement getCity;
    @FindBy(id = "calc_shipping_postcode")@CacheLookup private WebElement getPostalCode;
    @FindBy(css = ".shipping-calculator-button")@CacheLookup private WebElement getShippingChangeBtn;
    @FindBy(xpath = "//*[@name='calc_shipping']")@CacheLookup private WebElement updateShippingAddressBtn;
    private By overlay = By.cssSelector(".blockUI.blockOverlay");
    @FindBy(xpath = "//*[@class='woocommerce-shipping-destination']//strong")@CacheLookup private WebElement getUpdatedShippingAddress;
   // @FindBy(css = ".has-text-align-center") private WebElement cartHeading;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public boolean isLoaded(){
        return wait.until(ExpectedConditions.textToBe(cartHeading,"Cart"));
    }

    public CartPage load(){
        load("/cart");
        wait.until(ExpectedConditions.titleContains("Cart"));
        return this;
    }

    public String getProductNameFromCart(){
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
    }

    public CheckoutPage checkout(){
        wait.until(ExpectedConditions.elementToBeClickable(checkOutBtn)).click();
        return new CheckoutPage(driver);
    }
    public CartPage selectCountry(String countryName){
        wait.until(ExpectedConditions.elementToBeClickable(getCountryDrp)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='"+countryName+"']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",e);
        e.click();
        return this;
    }

    public CartPage selectState(String stateName){

        wait.until(ExpectedConditions.elementToBeClickable(getStateDrp)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='"+stateName+"']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",e);
        e.click();
        return this;
    }

    public CartPage enterCity(String cityName){
        wait.until(ExpectedConditions.elementToBeClickable(getCity));
        getCity.clear();
        getCity.sendKeys(cityName);
        return this;
    }

    public CartPage enterPostCode(String postcode){
        wait.until(ExpectedConditions.elementToBeClickable(getPostalCode));
        getPostalCode.clear();
        getPostalCode.sendKeys(postcode);
        return this;
    }

    public CartPage selectChangeShippingAddress(){
        getShippingChangeBtn.click();
        return this;
    }

    public CartPage setShippingAddress(ShippingAddress shippingAddress){
        return selectCountry(shippingAddress.getCountry())
                .selectState(shippingAddress.getState())
                .enterCity(shippingAddress.getCity())
                .enterPostCode(shippingAddress.getPostCode());
    }

    public CartPage updateShippingAddress(){

        wait.until(ExpectedConditions.elementToBeClickable(updateShippingAddressBtn)).click();
        return this;
    }

    public String getUpdatedShippingAddress(){
        waitForOverlayToDisappear(overlay);
        return wait.until(ExpectedConditions.elementToBeClickable(getUpdatedShippingAddress)).getText();
    }
}
