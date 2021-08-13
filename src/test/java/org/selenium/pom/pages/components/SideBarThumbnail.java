package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class SideBarThumbnail extends BasePage {

    private By bestSeller = By.xpath("//*[@id='woocommerce_top_rated_products-3']/h2");
    private By bestSellerProductTitle = By.xpath("//*[@class='product_list_widget']//li//a//span");




    public SideBarThumbnail(WebDriver driver) {
        super(driver);
    }

    public String getBestSellerTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bestSeller)).getText();

    }

    public List<String> getAllBestSellerProductTitle(){
         List<String> productsTitle = new ArrayList<String>();
         List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bestSellerProductTitle));
         for(WebElement e:list){
             productsTitle.add(e.getText());
         }
         return productsTitle;
    }

}
