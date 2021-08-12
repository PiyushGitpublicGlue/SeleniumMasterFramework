package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.*;

public class MyHeader extends BasePage {

    private By homeStoreLink = By.id("menu-item-1227");
    private By homeMensLink = By.id("menu-item-1228");
    private By homeWomenLink = By.id("menu-item-1229");
    private By homeAccessoriesLink = By.id("menu-item-1230");
    private By homeAccountLink = By.id("menu-item-1237");
    private By homeAboutLink = By.id("menu-item-1232");
    private By homeContactUsLink = By.id("menu-item-1233");
    private By miniCart = By.cssSelector(".cart-container");
    private By productTitleOnMiniCart = By.xpath("//div[@class='site-primary-header-wrap ast-builder-grid-row-container site-header-focus-item ast-container']//a[normalize-space()='Blue Shoes']");
    private By getSubTotalFromMiniCart = By.xpath("//*[contains(text(),'Subtotal:')]//..//span//bdi[1]");

    public MyHeader(WebDriver driver) {
        super(driver);
    }

    public StorePage navigateToStoreUsingMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(homeStoreLink)).click();
        return new StorePage(driver);
    }
    public MensPage navigateToMenSectionUsingMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(homeMensLink)).click();
        return new MensPage(driver);
    }
    public WomenPage navigateToWomenSectionUsingMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(homeWomenLink)).click();
        return new WomenPage(driver);
    }
    public AccessoriesPage navigateToAccessoriesSectionUsingMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(homeAccessoriesLink)).click();
        return new AccessoriesPage(driver);
    }
    public AccountPage navigateToAccountSectionUsingMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(homeAccountLink)).click();
        return new AccountPage(driver);
    }
    public AboutUsPage navigateToAboutSectionUsingMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(homeAboutLink)).click();
        return new AboutUsPage(driver);
    }
    public ContactUsPage navigateToContactUsSectionUsingMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(homeContactUsLink)).click();
        return new ContactUsPage(driver);
    }

    public MyHeader hoverOverMiniCart() {

        Actions action = new Actions(driver);
        action.moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(miniCart))).build().perform();
        return this;
    }

    public By getProductTitleOnMiniCartByElement(String productName){
        return By.xpath("//div[@class='site-primary-header-wrap ast-builder-grid-row-container site-header-focus-item ast-container']//a[normalize-space()='"+productName+"']");
    }

    public String getProductTitleOnMiniCart(String productName){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(getProductTitleOnMiniCartByElement(productName))).getText();

    }

    public double getSubTotalFromMiniCart(){
        String subTotal=wait.until(ExpectedConditions.visibilityOfElementLocated(getSubTotalFromMiniCart)).getText();
        subTotal = subTotal.replaceAll("\\$","");
        return Double.parseDouble(subTotal);
    }




}
