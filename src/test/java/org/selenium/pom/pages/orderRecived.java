package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.MyHeader;

public class orderRecived extends BasePage {

    private By orderPlacedVerify = By.cssSelector(".woocommerce-notice");
    private By getOrderNumber = By .xpath("//*[@class='woocommerce-order-overview__order order']//strong");
    private MyHeader myHeader;

    public MyHeader getMyHeader() {
        return myHeader;
    }

    public void setMyHeader(MyHeader myHeader) {
        this.myHeader = myHeader;
    }

    public orderRecived(WebDriver driver) {

        super(driver);
        myHeader = new MyHeader(driver);
    }

    public String verifyOrder(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderPlacedVerify)).getText();
    }

    public String getOrderNumber(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(getOrderNumber)).getText();
    }


}
