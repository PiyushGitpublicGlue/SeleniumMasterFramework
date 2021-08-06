package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.StorePage;

public class MyHeader extends BasePage {

    private By homeStoreLink = By.id("menu-item-1227");

    public MyHeader(WebDriver driver) {
        super(driver);
    }

    public StorePage nativateToStoreUsingMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(homeStoreLink)).click();
        return new StorePage(driver);
    }


}
