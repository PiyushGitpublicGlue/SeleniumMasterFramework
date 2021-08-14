package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class SideBarThumbnail extends BasePage {

    private By bestSeller = By.xpath("//*[@id='woocommerce_top_rated_products-3']/h2");
    private By bestSellerProductTitle = By.xpath("//*[@class='product_list_widget']//li//a//span");
    private By CategoriesTitle = By.xpath("//*[@id='woocommerce_product_categories-3']/h2");
    private By selectCategoriesDropDwn = By.id("product_cat");


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

    public String getCategoriesTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(CategoriesTitle)).getText();
    }

    public WebElement getSelectCategoriesDropDwn() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selectCategoriesDropDwn));
    }



    public void selectFromCategoryDropDwn(String category){
        Select sl = new Select(getSelectCategoriesDropDwn());
        //List<WebElement> listOfCategories = sl.getOptions();
        sl.selectByValue(category);

    }
}
