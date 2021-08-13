package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.testng.Assert;

import java.util.List;

public class MyFooter extends BasePage {

    private By footerQuickLinksCheck = By.xpath("//*[@id='menu-quick-links']//li//a");
    private By footerHorHerLinksCheck = By.xpath("//*[@id='menu-for-her']//li//a");
    private By footerHorHimLinksCheck = By.xpath("//*[@id='menu-for-him']//li//a");
    private By footerAppStoreLinksCheck = By.xpath("//*[@class='textwidget']//p//a");
    private By footerLinks = By.xpath("//*[@class='ast-builder-grid-row-container-inner']//a");
    String clicklnk = Keys.chord(Keys.CONTROL, Keys.ENTER);

    public MyFooter(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getFooterLinks(){
        List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(footerLinks));
        return list;
    }

    public List<WebElement> getFooterQuickLinks(){
        List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(footerQuickLinksCheck));
        return list;
    }

    public List<WebElement> getFooterHerLinks(){
        List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(footerHorHerLinksCheck));
        return list;
    }

    public List<WebElement> getFooterHimLinks(){
        List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(footerHorHimLinksCheck));
        return list;
    }

    public List<WebElement> getFooterAppLinks(){
        List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(footerAppStoreLinksCheck));
        return list;
    }



    public MyFooter footerQuickLinks() {
        for (WebElement e : getFooterQuickLinks()) {
            e.sendKeys(clicklnk);
            System.out.println("URL IN href : "+e.getAttribute("href"));
            driverWindowSwitch();

        }
        return this;
    }
    public MyFooter footerHerLinks() {
        for (WebElement e : getFooterHerLinks()) {
            e.sendKeys(clicklnk);
            System.out.println("URL IN href : "+e.getAttribute("href"));
            driverWindowSwitch();
        }
        return this;
    }
    public MyFooter footerHimLinks() {
        for (WebElement e : getFooterHimLinks()) {
            e.sendKeys(clicklnk);
            System.out.println("URL IN href : "+e.getAttribute("href"));
            driverWindowSwitch();
        }
        return this;
    }

    public MyFooter footerAppsLinks() {
        for (WebElement e : getFooterAppLinks()) {
            e.sendKeys(clicklnk);
            System.out.println("URL IN href : "+e.getAttribute("href"));
            driverWindowSwitch();
        }
        return this;
    }

    public MyFooter footerLinksCheck() {
        for (WebElement e : getFooterLinks()) {
            e.sendKeys(clicklnk);
            System.out.println("URL IN href : "+e.getAttribute("href"));
            String windowUrl = driverWindowSwitch();
            Assert.assertEquals(windowUrl,e.getAttribute("href"));
        }
        return this;
    }

    public String driverWindowSwitch() {
        //Current window handle
        String winHandleBefore = driver.getWindowHandle();
        //Handle new open window
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);}
            //perform action on new window
            String windowUrl = driver.getCurrentUrl();
            System.out.println("URL OF WINDOW : "+driver.getCurrentUrl());
            System.out.println("TITLE OF WINDOW : "+driver.getTitle());
            //close new window
            driver.close();
            // Switch back to original browser (first window)
            driver.switchTo().window(winHandleBefore);
            return windowUrl;
        }



    }





