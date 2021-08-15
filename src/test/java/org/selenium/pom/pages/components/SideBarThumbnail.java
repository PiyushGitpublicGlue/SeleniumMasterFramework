package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.Categories;

import java.util.ArrayList;
import java.util.List;

public class SideBarThumbnail extends BasePage {

    private By bestSeller = By.xpath("//*[@id='woocommerce_top_rated_products-3']/h2");
    private By bestSellerProductTitle = By.xpath("//*[@class='product_list_widget']//li//a//span");
    private By CategoriesTitle = By.xpath("//*[@id='woocommerce_product_categories-3']/h2");
    private By selectCategoriesDropDwn = By.id("product_cat");
    private By priceFilterTitle = By.xpath("//*[@id='woocommerce_price_filter-3']/h2");
    private By priceOnLeftSlider = By.xpath("//*[@class='price_label']//span[1]");
    private By priceOnRightSlider= By.xpath("//*[@class='price_label']//span[2]");
    private By leftSlider = By.xpath("//*[@class='ui-slider-handle ui-corner-all ui-state-default'][1]");
    private By applyFilter = By.xpath("//button[normalize-space()='Filter']");



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

    public double getPriceOnLeftSlider() {
        return Double.parseDouble(wait.until(ExpectedConditions.visibilityOfElementLocated(priceOnLeftSlider)).getText().replaceAll("\\$",""));
    }

    public double getPriceOnRightSlider() {
        return Double.parseDouble(wait.until(ExpectedConditions.visibilityOfElementLocated(priceOnRightSlider)).getText().replaceAll("\\$",""));
    }

    public String getPriceFilterTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(priceFilterTitle)).getText();
    }



    public SideBarThumbnail selectFromCategoryDropDwn(String category){
        Select sl = new Select(getSelectCategoriesDropDwn());
        sl.selectByValue(category);

        return this;
    }

        public SideBarThumbnail setCategories(Categories categories){
            return selectFromCategoryDropDwn(categories.getCategory());
        }

        protected WebElement getLeftSlider(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(leftSlider));
        }

        public SideBarThumbnail handlePriceSlider(int noOfSlide){
            for (int i = 1; i <= noOfSlide ; i++) {
                getLeftSlider().sendKeys(Keys.ARROW_RIGHT);
                wait.until(ExpectedConditions.visibilityOfElementLocated(applyFilter)).click();
            }
            return this;
        }



    }

