package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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


}
