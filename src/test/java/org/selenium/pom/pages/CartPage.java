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
import org.selenium.pom.objects.Coupons;
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
    private By getTotal = By.xpath("//tr[@class='order-total']//bdi[1]");
    private By subTotal = By.xpath("//tr[@class='cart-subtotal']//bdi[1]");
    private By getCouponCodeField = By.id("coupon_code");
    private By getCouponCodeApplyBtn = By.cssSelector("button[value='Apply coupon']");
    private By couponAmount = By.xpath("//tbody/tr[@class='cart-discount coupon-off25']/td[1]/span[1]");
    private By getSuccessMsgAfterCouponApply = By.xpath("//div[@role='alert']");
    private By getSateTaxes = By.xpath("//*[contains(@class,'tax-rate')]//span");
   // @FindBy(css = ".has-text-align-center") private WebElement cartHeading;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    private By getCouponAppliedByElement(String couponName){

        return By.cssSelector("tbody/tr[@class='cart-discount coupon-“"+couponName+"”']/td[1]/span[1]");

    }


    public String getCouponAmount(String couponName){
        By couponAmount = By.xpath("//td[@data-title='Coupon: "+couponName+"']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(couponAmount)).getText();
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

    public double verifyStateTaxes(ShippingAddress shippingAddress){
        return shippingAddress.getSalesTax();
    }

    public CartPage setCoupons(Coupons coupons){
        return getCouponCodeField(coupons.getCoupon());
    }

    public CartPage updateShippingAddress(){

        wait.until(ExpectedConditions.elementToBeClickable(updateShippingAddressBtn)).click();
        return this;
    }

    public String getUpdatedShippingAddress(){
        waitForOverlayToDisappear(overlay);
        return wait.until(ExpectedConditions.elementToBeClickable(getUpdatedShippingAddress)).getText();
    }

    public String getTotal(){

        return wait.until(ExpectedConditions.visibilityOfElementLocated(getTotal)).getText();
    }

    public CartPage getCouponCodeField(String coupon){
        wait.until(ExpectedConditions.visibilityOfElementLocated(getCouponCodeField)).sendKeys(coupon);
        return this;
    }

    public CartPage getCouponCodeApplyBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(getCouponCodeApplyBtn)).click();
        return this;
    }


    public String getSuccessMsgAfterCouponApply(){
        waitForOverlayToDisappear(overlay);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(getSuccessMsgAfterCouponApply)).getText();
    }

    public double getStateTax(){
        if(driver.findElements(getSateTaxes).size()==0){
            return 0.0;
        }
        return Double.parseDouble(wait.until(ExpectedConditions.visibilityOfElementLocated(getSateTaxes)).getText().replaceAll("\\$",""));
    }

    public double getSubTotal(){
        return Double.parseDouble(wait.until(ExpectedConditions.visibilityOfElementLocated(subTotal)).getText().replaceAll("\\$",""));
    }
}
