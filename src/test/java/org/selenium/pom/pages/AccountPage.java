package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class AccountPage extends BasePage {


    private By title = By.cssSelector(".has-text-align-center");
    private By getOrderSection = By.xpath("//a[normalize-space()='Orders']");
    private By viewOrderNumber = By.xpath("//tbody//tr//td//a");

    public AccountPage(WebDriver driver) {
        super(driver);
    }


    public boolean isLoaded(){

        return wait.until(ExpectedConditions.urlContains("/account"));
    }


    public AccountPage load(){

        load("/account");
        return this;
    }



    public String getTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    public AccountPage getMyOrderSection(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(getOrderSection)).click();
        return this;
    }

    public String viewOrderNumber(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(viewOrderNumber)).getText();
    }


}
