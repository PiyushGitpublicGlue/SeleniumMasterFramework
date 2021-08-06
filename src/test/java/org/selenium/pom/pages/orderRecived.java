package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class orderRecived extends BasePage {

    private By orderPlacedVerify = By.cssSelector(".woocommerce-notice");

    public orderRecived(WebDriver driver) {
        super(driver);
    }

    public String verifyOrder(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderPlacedVerify)).getText();
    }
}
