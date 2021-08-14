package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

import java.util.List;

public class ProductSearch extends BasePage {

    private By searchBox = By.id("woocommerce-product-search-field-0");
    private By searchBnt = By.cssSelector("button[value='Search']");
    private By totalProductAfterSearch = By.cssSelector(".woocommerce-loop-product__title");
    private By productOnPageTitle = By.cssSelector(".woocommerce-result-count");
    private By nextPage = By.xpath("//a[contains(text(),'→')]");



    public ProductSearch(WebDriver driver) {
        super(driver);
    }


    public void search(String Text){
        enterInSearchBox(Text).clickInSearchBtn();
    }

    public ProductSearch enterInSearchBox(String Text){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys(Text);
        return this;
    }

    public ProductSearch clickInSearchBtn(){

        wait.until(ExpectedConditions.elementToBeClickable(searchBnt)).click();
        return this;
    }

    public List<WebElement> getTotalProductAfterSearch(){
        return driver.findElements(totalProductAfterSearch);
    }

    public int searchedProductCount(){

        return getTotalProductAfterSearch().size();

    }

    public String getTotalProductsOnPageTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productOnPageTitle)).getText();
    }



    public int searchedProductList(){
        List<String> productList = null;
        int count=0;
        List<WebElement> productListWebElements = driver.findElements(totalProductAfterSearch);
        for (WebElement e:productListWebElements
             ) {
            System.out.println(e.getText());
            count++;
        }
        return count;

    }

    public int productOnPage(){

        int count=0;
        List<WebElement> productListWebElements = driver.findElements(totalProductAfterSearch);
        System.out.println("TOTAL PRODUCTS ON PAGE : "+getTotalProductsOnPageTitle());
        for (WebElement e:productListWebElements) {
            System.out.println(e.getText());
            count++;
        }
        if(driver.findElements(nextPage).size()>=1)
        {
            driver.findElement(nextPage).click();
            List<WebElement> productListWebElementsNext = driver.findElements(totalProductAfterSearch);
            for (WebElement e:productListWebElementsNext) {
                System.out.println("PRODUCT NAME ON PAGE : "+e.getText());
                count++;
            }
        }
        System.out.println("TOTAL PRODUCT COUNT : "+count);
        return count;

    }



}
