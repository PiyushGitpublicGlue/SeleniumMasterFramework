package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class ContactUsPage extends BasePage {


    private By title = By.cssSelector(".has-text-align-center");

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }


    public boolean isLoaded(){

        return wait.until(ExpectedConditions.urlContains("/contact-us"));
    }


    public ContactUsPage load(){

        load("/contact-us");
        return this;
    }



    public String getTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }


}
