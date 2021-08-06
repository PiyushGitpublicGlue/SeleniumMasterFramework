package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;

public class CheckoutPage extends BasePage {

    private By firstName = By.id("billing_first_name");
    private By lastName = By.id("billing_last_name");
    private By company = By.id("billing_company");
    private By address1 = By.id("billing_address_1");
    private By address2 = By.id("billing_address_2");
    private By city = By.id("billing_city");
    private By postCode = By.id("billing_postcode");
    private By phoneNumber = By.id("billing_phone");
    private By email = By.id("billing_email");
    private By orderBtn = By.id("place_order");
    private By clickOnLogin = By.cssSelector(".showlogin");
    private By enterUserName = By.id("username");
    private By enterPassword = By.id("password");
    private By clickOnLoginBtn = By.xpath("//*[@name='login']");
    private By overlay = By.cssSelector(".blockUI.blockOverlay");
    //private By selectCountry = By.id("billing_country");
    //private By selectState = By.id("billing_state");
    private By directBankTransferRadioBtn = By.id("payment_method_bacs");
    private By selectCountry = By.id("select2-billing_country-container");
    private By selectState = By.id("select2-billing_state-container");
    private By productName = By.cssSelector("td[class='product-name']");


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load(){
        load("/checkout/");
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress){
        return enterFirstName(billingAddress.getFistName()).
                enterLastName(billingAddress.getLastName()).
                enterCompanyName(billingAddress.getCompanyName())
                .selectCountry(billingAddress.getCountry())
                .enterAddress1(billingAddress.getAddress1())
                .enterAddress2(billingAddress.getAddress2())
                .enterCity(billingAddress.getCity())
                .selectState(billingAddress.getState())
                .enterPostCode(billingAddress.getPostCode())
                .enterPhoneNumber(billingAddress.getPhoneNumber())
                .enterEmail(billingAddress.getEmail());
    }

    public CheckoutPage enterFirstName(String fName){

        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        e.clear();
        e.sendKeys(fName);
        return this;
    }

    public CheckoutPage enterLastName(String lName){

        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
        e.clear();
        e.sendKeys(lName);
        return this;
    }

    public CheckoutPage enterCompanyName(String companyName){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(company));
        e.clear();
        e.sendKeys(companyName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName){
        // select = new Select(wait.until(ExpectedConditions.elementToBeClickable(selectCountry)));
        //select.selectByVisibleText(countryName);
        wait.until(ExpectedConditions.elementToBeClickable(selectCountry)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='"+countryName+"']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",e);
        e.click();
        return this;
    }



    public CheckoutPage enterAddress1(String addressLine1){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(address1));
        e.clear();
        e.sendKeys(addressLine1);
        return this;
    }

    public CheckoutPage enterAddress2(String addressLine2){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(address2));
        e.clear();
        e.sendKeys(addressLine2);
        return this;
    }

    public CheckoutPage enterCity(String cityName){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(city));
        e.clear();
        e.sendKeys(cityName);
        return this;
    }

    public CheckoutPage selectState(String stateName){
        //Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(selectState)));
        //select.selectByVisibleText(stateName);
        wait.until(ExpectedConditions.elementToBeClickable(selectState)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='"+stateName+"']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",e);
        e.click();
        return this;
    }

    public CheckoutPage enterPostCode(String postcode){

        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(postCode));
        e.clear();
        e.sendKeys(postcode);
        return this;
    }

    public CheckoutPage enterEmail(String emailAddress){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(email));
        e.clear();
        e.sendKeys(emailAddress);
        return this;
    }

    public CheckoutPage enterPhoneNumber(String phone){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumber));
        e.clear();
        e.sendKeys(phone);
        return this;
    }

    public orderRecived clickOnOrderBtn(){

        waitForOverlayToDisappear(overlay);
        wait.until(ExpectedConditions.elementToBeClickable(orderBtn)).click();
        return new orderRecived(driver);
    }

    private CheckoutPage waitForLoginButtonToDisappear(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(clickOnLoginBtn));
        return this;
    }

    public CheckoutPage doLogin(String username, String password){

        wait.until(ExpectedConditions.elementToBeClickable(clickOnLogin)).click();
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(enterUserName));
        usernameElement.clear();
        usernameElement.sendKeys(username);
        driver.findElement(enterPassword).sendKeys(password);
        WebElement passElement = wait.until(ExpectedConditions.visibilityOfElementLocated(enterPassword));
        passElement.clear();
        passElement.sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(clickOnLoginBtn)).click();
        waitForLoginButtonToDisappear();
        return this;
    }

    public CheckoutPage selectDirectBankTransferBtn(){
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if(!e.isSelected()){
            e.click();
        }
        return this;
    }

    public String getProductName(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }


}
