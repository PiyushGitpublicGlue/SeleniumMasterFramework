package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.utils.ConfigLoader;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void load(String endpoint) {
        driver.get(ConfigLoader.getInstance().getBaseUrl() +endpoint);
    }

    public void waitForOverlayToDisappear(By overlay){
        List<WebElement> overlayList = driver.findElements(overlay);
        System.out.println("SIZE OF OVERLAY : "+overlayList.size());
        if(overlayList.size()>0){
           wait.until(ExpectedConditions.invisibilityOfAllElements(overlayList));
            System.out.println("OVERLAYS ARE INVISIBLE");
        }
        else {
            System.out.println("NO OVERLAYS FOUND");
        }
    }
}
